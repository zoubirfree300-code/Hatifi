package com.example.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.PhoneRepository
import com.example.data.PhoneRepositoryImpl
import com.example.data.SyncStatus
import com.example.model.Phone
import com.example.model.PhoneCategory
import com.example.model.RecommendationStatus
import com.example.util.AppLanguage
import com.example.util.CurrencyHelper
import com.example.util.CurrencyMode
import com.example.worker.PhoneSyncWorker
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class PriceFilter {
    ANY,
    UNDER_200,
    BETWEEN_200_500,
    BETWEEN_500_900,
    OVER_900
}

enum class SortOption(val titleEn: String, val titleAr: String) {
    NEWEST("Newest First", "الأحدث أولاً"),
    PRICE_LOW_TO_HIGH("Price: Low to High", "السعر: من الأقل للأعلى"),
    PRICE_HIGH_TO_LOW("Price: High to Low", "السعر: من الأعلى للأقل"),
    VALUE_SCORE("Best Value for Money", "الأعلى قيمة مقابل السعر");

    fun getTitle(isArabic: Boolean): String = if (isArabic) titleAr else titleEn
}

data class FilterCriteria(
    val query: String = "",
    val brand: String = "All",
    val category: PhoneCategory = PhoneCategory.ALL,
    val priceFilter: PriceFilter = PriceFilter.ANY,
    val year: Int? = null,
    val verdict: RecommendationStatus? = null,
    val sortOption: SortOption = SortOption.NEWEST
)

class HatifiViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PhoneRepository = PhoneRepositoryImpl(application)
    private val prefs = application.getSharedPreferences("hatifi_ui_prefs", Context.MODE_PRIVATE)

    // Language state (persisted)
    private val _appLanguage = MutableStateFlow(
        if (prefs.getString("app_lang", "ar") == "en") AppLanguage.ENGLISH else AppLanguage.ARABIC
    )
    val appLanguage: StateFlow<AppLanguage> = _appLanguage.asStateFlow()

    // Dark theme state
    private val _isDarkTheme = MutableStateFlow(prefs.getBoolean("dark_theme", true))
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    // Currency Settings (DZD rate & display mode)
    private val _currencyMode = MutableStateFlow(
        try {
            CurrencyMode.valueOf(prefs.getString("currency_mode", CurrencyMode.BOTH.name) ?: CurrencyMode.BOTH.name)
        } catch (e: Exception) {
            CurrencyMode.BOTH
        }
    )
    val currencyMode: StateFlow<CurrencyMode> = _currencyMode.asStateFlow()

    private val _dzdRate = MutableStateFlow(
        prefs.getFloat("dzd_rate", CurrencyHelper.DEFAULT_DZD_RATE.toFloat()).toDouble()
    )
    val dzdRate: StateFlow<Double> = _dzdRate.asStateFlow()

    // Filter criteria state
    private val _filterCriteria = MutableStateFlow(FilterCriteria())
    val filterCriteria: StateFlow<FilterCriteria> = _filterCriteria.asStateFlow()

    // Convenience delegates for UI bindings
    val searchQuery: StateFlow<String> = _filterCriteria.map { it.query }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")
    val selectedBrand: StateFlow<String> = _filterCriteria.map { it.brand }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "All")
    val selectedCategory: StateFlow<PhoneCategory> = _filterCriteria.map { it.category }
        .stateIn(viewModelScope, SharingStarted.Eagerly, PhoneCategory.ALL)
    val selectedPriceFilter: StateFlow<PriceFilter> = _filterCriteria.map { it.priceFilter }
        .stateIn(viewModelScope, SharingStarted.Eagerly, PriceFilter.ANY)
    val selectedYear: StateFlow<Int?> = _filterCriteria.map { it.year }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val selectedVerdict: StateFlow<RecommendationStatus?> = _filterCriteria.map { it.verdict }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)
    val selectedSort: StateFlow<SortOption> = _filterCriteria.map { it.sortOption }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SortOption.NEWEST)

    // Navigation Destinations: 0: Home, 1: Catalog, 2: Compare, 3: Favorites, 4: Settings
    private val _currentNavDestination = MutableStateFlow(0)
    val currentNavDestination: StateFlow<Int> = _currentNavDestination.asStateFlow()

    // Detail Screen Selection
    private val _selectedPhoneDetail = MutableStateFlow<Phone?>(null)
    val selectedPhoneDetail: StateFlow<Phone?> = _selectedPhoneDetail.asStateFlow()

    // Comparison Selection (2 to 3 phones)
    private val _comparisonList = MutableStateFlow<List<Phone>>(emptyList())
    val comparisonList: StateFlow<List<Phone>> = _comparisonList.asStateFlow()

    // Favorites from Room
    val favoritesList: StateFlow<List<Phone>> = repository.favoritesFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Filter Sheet Visibility
    private val _showFilterSheet = MutableStateFlow(false)
    val showFilterSheet: StateFlow<Boolean> = _showFilterSheet.asStateFlow()

    val syncStatus: StateFlow<SyncStatus> = repository.syncStatusFlow

    private var currentPage = 1

    init {
        // Schedule WorkManager 24-hour sync
        PhoneSyncWorker.schedulePeriodicSync(application)

        // Try to fetch latest live DZD exchange rate in background
        viewModelScope.launch {
            val liveRate = CurrencyHelper.fetchLiveDzdRate()
            if (liveRate != null && liveRate > 0) {
                _dzdRate.value = liveRate
                prefs.edit().putFloat("dzd_rate", liveRate.toFloat()).apply()
            }
        }
    }

    // Filtered and Sorted Phones computation combining repository flow and filter criteria
    val filteredPhones: StateFlow<List<Phone>> = repository.phonesFlow.combine(_filterCriteria) { phones, filters ->
        val filtered = phones.filter { phone ->
            val matchesQuery = filters.query.isBlank() ||
                    phone.name.contains(filters.query, ignoreCase = true) ||
                    phone.brand.contains(filters.query, ignoreCase = true) ||
                    phone.specs.chipset.name.contains(filters.query, ignoreCase = true)

            val matchesBrand = filters.brand == "All" || phone.brand.equals(filters.brand, ignoreCase = true)
            val matchesCategory = filters.category == PhoneCategory.ALL || phone.category == filters.category

            val matchesPrice = when (filters.priceFilter) {
                PriceFilter.ANY -> true
                PriceFilter.UNDER_200 -> phone.approxPriceUsd < 200
                PriceFilter.BETWEEN_200_500 -> phone.approxPriceUsd in 200..500
                PriceFilter.BETWEEN_500_900 -> phone.approxPriceUsd in 501..900
                PriceFilter.OVER_900 -> phone.approxPriceUsd > 900
            }

            val matchesYear = filters.year == null || phone.releaseYear == filters.year
            val matchesVerdict = filters.verdict == null || phone.verdict.status == filters.verdict

            matchesQuery && matchesBrand && matchesCategory && matchesPrice && matchesYear && matchesVerdict
        }

        when (filters.sortOption) {
            SortOption.NEWEST -> filtered.sortedByDescending { it.releaseYear * 10000 + it.approxPriceUsd }
            SortOption.PRICE_LOW_TO_HIGH -> filtered.sortedBy { it.approxPriceUsd }
            SortOption.PRICE_HIGH_TO_LOW -> filtered.sortedByDescending { it.approxPriceUsd }
            SortOption.VALUE_SCORE -> filtered.sortedByDescending { it.verdict.valueScore }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Latest phones computation
    val latestPhones: StateFlow<List<Phone>> = repository.phonesFlow.map { list ->
        list.filter { it.isLatest || it.releaseYear >= 2024 }
            .sortedByDescending { it.releaseYear * 10000 + it.approxPriceUsd }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeFiltersCount: StateFlow<Int> = _filterCriteria.map { f ->
        var count = 0
        if (f.brand != "All") count++
        if (f.category != PhoneCategory.ALL) count++
        if (f.priceFilter != PriceFilter.ANY) count++
        if (f.year != null) count++
        if (f.verdict != null) count++
        if (f.sortOption != SortOption.NEWEST) count++
        count
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun toggleLanguage() {
        val next = if (_appLanguage.value == AppLanguage.ARABIC) AppLanguage.ENGLISH else AppLanguage.ARABIC
        _appLanguage.value = next
        prefs.edit().putString("app_lang", next.code).apply()
    }

    fun toggleTheme() {
        val next = !_isDarkTheme.value
        _isDarkTheme.value = next
        prefs.edit().putBoolean("dark_theme", next).apply()
    }

    fun setCurrencyMode(mode: CurrencyMode) {
        _currencyMode.value = mode
        prefs.edit().putString("currency_mode", mode.name).apply()
    }

    fun setCustomDzdRate(rate: Double) {
        _dzdRate.value = rate
        prefs.edit().putFloat("dzd_rate", rate.toFloat()).apply()
    }

    fun setSearchQuery(q: String) {
        _filterCriteria.value = _filterCriteria.value.copy(query = q)
    }

    fun setSelectedBrand(brand: String) {
        _filterCriteria.value = _filterCriteria.value.copy(brand = brand)
    }

    fun setSelectedCategory(cat: PhoneCategory) {
        _filterCriteria.value = _filterCriteria.value.copy(category = cat)
    }

    fun setSelectedPriceFilter(p: PriceFilter) {
        _filterCriteria.value = _filterCriteria.value.copy(priceFilter = p)
    }

    fun setSelectedYear(year: Int?) {
        _filterCriteria.value = _filterCriteria.value.copy(year = year)
    }

    fun setSelectedVerdict(verdict: RecommendationStatus?) {
        _filterCriteria.value = _filterCriteria.value.copy(verdict = verdict)
    }

    fun setSortOption(sort: SortOption) {
        _filterCriteria.value = _filterCriteria.value.copy(sortOption = sort)
    }

    fun resetFilters() {
        _filterCriteria.value = FilterCriteria()
    }

    fun setNavDestination(dest: Int) {
        _currentNavDestination.value = dest
    }

    fun openPhoneDetail(phone: Phone) {
        _selectedPhoneDetail.value = phone
    }

    fun closePhoneDetail() {
        _selectedPhoneDetail.value = null
    }

    fun toggleFavorite(phone: Phone) {
        viewModelScope.launch {
            repository.toggleFavorite(phone.id)
        }
    }

    fun toggleComparison(phone: Phone) {
        val current = _comparisonList.value.toMutableList()
        val exists = current.any { it.id == phone.id }
        if (exists) {
            current.removeAll { it.id == phone.id }
        } else {
            if (current.size < 3) {
                current.add(phone)
            } else {
                current.removeAt(0)
                current.add(phone)
            }
        }
        _comparisonList.value = current
    }

    fun removeFromComparison(phone: Phone) {
        _comparisonList.value = _comparisonList.value.filter { it.id != phone.id }
    }

    fun clearComparison() {
        _comparisonList.value = emptyList()
    }

    fun setShowFilterSheet(show: Boolean) {
        _showFilterSheet.value = show
    }

    fun refreshData(force: Boolean = true) {
        viewModelScope.launch {
            repository.refresh(force = force)
        }
    }

    fun loadMorePhones() {
        viewModelScope.launch {
            currentPage++
            repository.loadMorePage(currentPage)
        }
    }

    fun getApiUrl(): String = repository.getApiUrl()

    fun updateApiUrl(newUrl: String) {
        repository.setApiUrl(newUrl)
        refreshData(force = true)
    }

    fun getLastSyncTimestamp(): Long = repository.getLastSyncTimestamp()
}
