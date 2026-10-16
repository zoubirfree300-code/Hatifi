package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.SyncStatus
import com.example.model.Phone
import com.example.model.PhoneCategory
import com.example.ui.components.*
import com.example.util.CurrencyHelper
import com.example.util.Strings
import com.example.viewmodel.HatifiViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HatifiViewModel,
    isArabic: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val currentNavDestination by viewModel.currentNavDestination.collectAsState()
    val filteredPhones by viewModel.filteredPhones.collectAsState()
    val latestPhones by viewModel.latestPhones.collectAsState()
    val comparisonList by viewModel.comparisonList.collectAsState()
    val favoritesList by viewModel.favoritesList.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedBrand by viewModel.selectedBrand.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val selectedPriceFilter by viewModel.selectedPriceFilter.collectAsState()
    val selectedYear by viewModel.selectedYear.collectAsState()
    val selectedVerdict by viewModel.selectedVerdict.collectAsState()
    val selectedSort by viewModel.selectedSort.collectAsState()
    val activeFiltersCount by viewModel.activeFiltersCount.collectAsState()
    val showFilterSheet by viewModel.showFilterSheet.collectAsState()
    val syncStatus by viewModel.syncStatus.collectAsState()
    val isDarkTheme by viewModel.isDarkTheme.collectAsState()
    val currencyMode by viewModel.currencyMode.collectAsState()
    val dzdRate by viewModel.dzdRate.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { viewModel.setNavDestination(0) }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hatifi_logo_1792105395050),
                            contentDescription = "Hatifi Logo",
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = Strings.appName(isArabic),
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    letterSpacing = 0.5.sp
                                )
                            )
                        }
                    }
                },
                actions = {
                    // Language Switcher (AR / EN)
                    FilterChip(
                        selected = true,
                        onClick = { viewModel.toggleLanguage() },
                        label = {
                            Text(
                                text = if (isArabic) "EN" else "العربية",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Language",
                                modifier = Modifier.size(14.dp)
                            )
                        },
                        modifier = Modifier
                            .height(34.dp)
                            .testTag("language_toggle_button")
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // Dark/Light Theme Switcher
                    IconButton(
                        onClick = { viewModel.toggleTheme() },
                        modifier = Modifier.testTag("theme_toggle_button")
                    ) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle Theme"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Column {
                // Floating Comparison Bar when phones are selected
                AnimatedVisibility(
                    visible = comparisonList.isNotEmpty() && currentNavDestination != 2,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    Surface(
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        tonalElevation = 6.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = Strings.compareNow(isArabic, comparisonList.size),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            )

                            Row {
                                TextButton(onClick = { viewModel.clearComparison() }) {
                                    Text(Strings.filterReset(isArabic))
                                }
                                Button(
                                    onClick = { viewModel.setNavDestination(2) },
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Icon(Icons.Default.CompareArrows, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = Strings.tabCompare(isArabic))
                                }
                            }
                        }
                    }
                }

                // Standard Material 3 NavigationBar
                NavigationBar(
                    modifier = Modifier.navigationBarsPadding(),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                    NavigationBarItem(
                        selected = currentNavDestination == 0,
                        onClick = { viewModel.setNavDestination(0) },
                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                        label = { Text(if (isArabic) "الرئيسية" else "Home") }
                    )
                    NavigationBarItem(
                        selected = currentNavDestination == 1,
                        onClick = { viewModel.setNavDestination(1) },
                        icon = { Icon(Icons.Default.Devices, contentDescription = "Catalog") },
                        label = { Text(if (isArabic) "الكتالوج" else "Catalog") }
                    )
                    NavigationBarItem(
                        selected = currentNavDestination == 2,
                        onClick = { viewModel.setNavDestination(2) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (comparisonList.isNotEmpty()) {
                                        Badge { Text(comparisonList.size.toString()) }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.CompareArrows, contentDescription = "Compare")
                            }
                        },
                        label = { Text(if (isArabic) "المقارنة" else "Compare") }
                    )
                    NavigationBarItem(
                        selected = currentNavDestination == 3,
                        onClick = { viewModel.setNavDestination(3) },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (favoritesList.isNotEmpty()) {
                                        Badge { Text(favoritesList.size.toString()) }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                            }
                        },
                        label = { Text(if (isArabic) "المفضلة" else "Favorites") }
                    )
                    NavigationBarItem(
                        selected = currentNavDestination == 4,
                        onClick = { viewModel.setNavDestination(4) },
                        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                        label = { Text(if (isArabic) "الإعدادات" else "Settings") }
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentNavDestination) {
                0 -> {
                    // 1. HOME SCREEN: Hero with Hatifi logo, "Latest phones" carousel, search bar, TikTok button
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        // Hero Header with official Hatifi Logo
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.hatifi_logo_1792105395050),
                                        contentDescription = "Hatifi Official Logo",
                                        modifier = Modifier
                                            .size(90.dp)
                                            .clip(CircleShape)
                                            .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape),
                                        contentScale = ContentScale.Fit
                                    )

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = if (isArabic) "هاتفي للهواتف الذكية" else "Hatifi Smart Phones",
                                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )

                                    Spacer(modifier = Modifier.height(4.dp))

                                    Text(
                                        text = Strings.appTagline(isArabic),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }
                        }

                        // Prominent Owner TikTok Banner
                        item {
                            TikTokBanner(isArabic = isArabic)
                        }

                        // Search Bar leading directly to Catalog
                        item {
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = {
                                    viewModel.setSearchQuery(it)
                                    if (it.isNotBlank()) viewModel.setNavDestination(1)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("home_search_input"),
                                placeholder = {
                                    Text(
                                        text = Strings.searchPlaceholder(isArabic),
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(16.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                    focusedContainerColor = MaterialTheme.colorScheme.surface
                                )
                            )
                        }

                        // "Latest Phones" Carousel Section
                        item {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Bolt,
                                            contentDescription = null,
                                            tint = Color(0xFFF59E0B),
                                            modifier = Modifier.size(22.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = Strings.tabLatest(isArabic),
                                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                        )
                                    }

                                    TextButton(onClick = { viewModel.setNavDestination(1) }) {
                                        Text(text = if (isArabic) "عرض الكل ←" else "View All →")
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    items(latestPhones.take(8), key = { it.id }) { phone ->
                                        LatestPhoneCarouselItem(
                                            phone = phone,
                                            isArabic = isArabic,
                                            currencyMode = currencyMode,
                                            dzdRate = dzdRate,
                                            onClick = { viewModel.openPhoneDetail(phone) }
                                        )
                                    }
                                }
                            }
                        }

                        // Categories Grid Header
                        item {
                            Text(
                                text = if (isArabic) "تصفح حسب الفئة" else "Browse by Category",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                PhoneCategory.values().forEach { cat ->
                                    FilterChip(
                                        selected = selectedCategory == cat,
                                        onClick = {
                                            viewModel.setSelectedCategory(cat)
                                            viewModel.setNavDestination(1)
                                        },
                                        label = { Text(cat.getTitle(isArabic)) }
                                    )
                                }
                            }
                        }

                        // Featured Top Phones
                        item {
                            Text(
                                text = if (isArabic) "هواتف مميزة ننصح بها" else "Featured Recommendations",
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        items(filteredPhones.take(6), key = { it.id }) { phone ->
                            PhoneCard(
                                phone = phone,
                                isArabic = isArabic,
                                isCompared = comparisonList.any { it.id == phone.id },
                                isFavorite = favoritesList.any { it.id == phone.id },
                                currencyMode = currencyMode,
                                dzdRate = dzdRate,
                                onPhoneClick = { viewModel.openPhoneDetail(it) },
                                onCompareToggle = { viewModel.toggleComparison(it) },
                                onFavoriteToggle = { viewModel.toggleFavorite(it) }
                            )
                        }

                        item {
                            FooterSection(isArabic = isArabic)
                        }
                    }
                }

                1 -> {
                    // 2. CATALOG SCREEN: Search, Filters, Sorting, Favorites, Infinite Scroll
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            FilterBar(
                                searchQuery = searchQuery,
                                onSearchChange = { viewModel.setSearchQuery(it) },
                                selectedBrand = selectedBrand,
                                onBrandSelect = { viewModel.setSelectedBrand(it) },
                                selectedCategory = selectedCategory,
                                onCategorySelect = { viewModel.setSelectedCategory(it) },
                                activeFiltersCount = activeFiltersCount,
                                onOpenFilterSheet = { viewModel.setShowFilterSheet(true) },
                                isArabic = isArabic
                            )
                        }

                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = Strings.foundPhones(isArabic, filteredPhones.size),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )

                                TextButton(
                                    onClick = { viewModel.setShowFilterSheet(true) }
                                ) {
                                    Icon(Icons.Default.Sort, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(text = selectedSort.getTitle(isArabic), style = MaterialTheme.typography.labelSmall)
                                }
                            }
                        }

                        if (filteredPhones.isEmpty()) {
                            item {
                                EmptyState(isArabic = isArabic, onReset = { viewModel.resetFilters() })
                            }
                        } else {
                            items(filteredPhones, key = { it.id }) { phone ->
                                PhoneCard(
                                    phone = phone,
                                    isArabic = isArabic,
                                    isCompared = comparisonList.any { it.id == phone.id },
                                    isFavorite = favoritesList.any { it.id == phone.id },
                                    currencyMode = currencyMode,
                                    dzdRate = dzdRate,
                                    onPhoneClick = { viewModel.openPhoneDetail(it) },
                                    onCompareToggle = { viewModel.toggleComparison(it) },
                                    onFavoriteToggle = { viewModel.toggleFavorite(it) }
                                )
                            }

                            // Infinite scroll / pagination trigger item
                            item {
                                Button(
                                    onClick = { viewModel.loadMorePhones() },
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(Icons.Default.Downloading, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = if (isArabic) "تحميل المزيد من الهواتف (Pagination)" else "Load More Phones")
                                }
                            }
                        }
                    }
                }

                2 -> {
                    // 3. COMPARE SCREEN: Up to 3 phones side-by-side
                    ComparisonScreen(
                        comparisonPhones = comparisonList,
                        onRemovePhone = { viewModel.removeFromComparison(it) },
                        onClearAll = { viewModel.clearComparison() },
                        onBrowsePhones = { viewModel.setNavDestination(1) },
                        isArabic = isArabic
                    )
                }

                3 -> {
                    // 4. FAVORITES SCREEN
                    FavoritesScreen(
                        favorites = favoritesList,
                        comparisonList = comparisonList,
                        currencyMode = currencyMode,
                        dzdRate = dzdRate,
                        isArabic = isArabic,
                        onPhoneClick = { viewModel.openPhoneDetail(it) },
                        onCompareToggle = { viewModel.toggleComparison(it) },
                        onFavoriteToggle = { viewModel.toggleFavorite(it) },
                        onExploreClick = { viewModel.setNavDestination(1) }
                    )
                }

                4 -> {
                    // 5. SETTINGS SCREEN
                    SettingsScreen(
                        viewModel = viewModel,
                        isArabic = isArabic
                    )
                }
            }
        }
    }

    // Filter BottomSheet
    if (showFilterSheet) {
        FilterBottomSheet(
            selectedPrice = selectedPriceFilter,
            onPriceSelect = { viewModel.setSelectedPriceFilter(it) },
            selectedYear = selectedYear,
            onYearSelect = { viewModel.setSelectedYear(it) },
            selectedVerdict = selectedVerdict,
            onVerdictSelect = { viewModel.setSelectedVerdict(it) },
            selectedSort = selectedSort,
            onSortSelect = { viewModel.setSortOption(it) },
            onReset = { viewModel.resetFilters() },
            onDismiss = { viewModel.setShowFilterSheet(false) },
            isArabic = isArabic
        )
    }
}

@Composable
private fun LatestPhoneCarouselItem(
    phone: Phone,
    isArabic: Boolean,
    currencyMode: com.example.util.CurrencyMode,
    dzdRate: Double,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                PhoneImageView(
                    phone = phone,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = Color(0xFFE11D48),
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "NEW",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = 9.sp
                        ),
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = phone.name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                maxLines = 1
            )

            Text(
                text = CurrencyHelper.formatPrice(phone.approxPriceUsd, dzdRate, currencyMode, isArabic),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
private fun EmptyState(isArabic: Boolean, onReset: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.SearchOff,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(56.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = Strings.noResults(isArabic),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = Strings.noResultsTip(isArabic),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(14.dp))
        FilledTonalButton(onClick = onReset) {
            Text(text = Strings.filterReset(isArabic))
        }
    }
}

@Composable
private fun FooterSection(isArabic: Boolean) {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { openTikTokProfile(context) },
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color(0xFFFE2C55),
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = Strings.tiktokFooter(isArabic),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "https://www.tiktok.com/@az.techno4",
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.primary)
            )
        }
    }
}
