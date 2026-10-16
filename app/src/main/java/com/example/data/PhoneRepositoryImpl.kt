package com.example.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.data.local.HatifiDatabase
import com.example.data.local.PhoneEntity
import com.example.model.*
import com.example.util.VerdictCalculator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class PhoneRepositoryImpl(
    private val context: Context,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : PhoneRepository {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("hatifi_prefs", Context.MODE_PRIVATE)

    private val db = HatifiDatabase.getInstance(context)
    private val phoneDao = db.phoneDao()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(ApiConfig.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(ApiConfig.NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    private val _phonesFlow = MutableStateFlow<List<Phone>>(emptyList())
    override val phonesFlow: StateFlow<List<Phone>> = _phonesFlow.asStateFlow()

    override val favoritesFlow: Flow<List<Phone>> = phoneDao.getFavoritePhonesFlow()
        .map { entities -> entities.map { mapEntityToPhone(it) } }

    private val _syncStatusFlow = MutableStateFlow<SyncStatus>(SyncStatus.Idle)
    override val syncStatusFlow: StateFlow<SyncStatus> = _syncStatusFlow.asStateFlow()

    init {
        // Collect from Room so UI is always reactive
        scope.launch {
            phoneDao.getAllPhonesFlow().collect { entities ->
                if (entities.isNotEmpty()) {
                    _phonesFlow.value = entities.map { mapEntityToPhone(it) }
                } else {
                    // Populate from bundled JSON seed
                    seedInitialDataIfEmpty()
                }
            }
        }

        // Automatic refresh on launch / 24 hours
        val lastSync = getLastSyncTimestamp()
        val now = System.currentTimeMillis()
        val shouldRefresh = (now - lastSync) > ApiConfig.REFRESH_INTERVAL_MILLIS || lastSync == 0L

        scope.launch {
            if (shouldRefresh) {
                refresh(force = false)
            } else {
                _syncStatusFlow.value = SyncStatus.Success(
                    timestamp = lastSync,
                    count = _phonesFlow.value.size,
                    isRemote = false
                )
            }
        }
    }

    private suspend fun seedInitialDataIfEmpty() = withContext(Dispatchers.IO) {
        if (phoneDao.getCount() == 0) {
            try {
                val jsonString = context.assets.open("seed_phones.json").bufferedReader().use { it.readText() }
                val parsed = parsePhonesJson(jsonString)
                if (parsed.isNotEmpty()) {
                    phoneDao.insertAll(parsed.map { mapPhoneToEntity(it) })
                    return@withContext
                }
            } catch (e: Exception) {
                Log.w("PhoneRepository", "Failed to read seed_phones.json from assets, using static seed", e)
            }
            phoneDao.insertAll(SeedPhonesData.phones.map { mapPhoneToEntity(it) })
        }
    }

    override fun getApiUrl(): String {
        return prefs.getString("custom_api_url", ApiConfig.DEFAULT_API_URL) ?: ApiConfig.DEFAULT_API_URL
    }

    override fun setApiUrl(url: String) {
        prefs.edit().putString("custom_api_url", url.trim()).apply()
    }

    override fun getLastSyncTimestamp(): Long {
        return prefs.getLong("last_sync_timestamp", 0L)
    }

    private fun setLastSyncTimestamp(timestamp: Long) {
        prefs.edit().putLong("last_sync_timestamp", timestamp).apply()
    }

    override suspend fun toggleFavorite(phoneId: String) = withContext(Dispatchers.IO) {
        val current = phoneDao.getPhoneById(phoneId) ?: return@withContext
        val newFav = !current.isFavorite
        phoneDao.setFavorite(phoneId, newFav)
    }

    override suspend fun loadMorePage(page: Int): Int = withContext(Dispatchers.IO) {
        // Appends next page if API supports pagination
        val targetUrl = getApiUrl()
        val pagedUrl = if (targetUrl.contains("?")) "$targetUrl&page=$page&limit=15" else "$targetUrl?page=$page&limit=15"
        try {
            val request = Request.Builder()
                .url(pagedUrl)
                .header("User-Agent", "Hatifi-Android-App/1.0")
                .header("Accept", "application/json")
                .build()

            httpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val body = response.body?.string() ?: return@withContext 0
                    val phones = parsePhonesJson(body)
                    if (phones.isNotEmpty()) {
                        phoneDao.insertAll(phones.map { mapPhoneToEntity(it) })
                        return@withContext phones.size
                    }
                }
            }
        } catch (e: Exception) {
            Log.w("PhoneRepository", "loadMorePage failed", e)
        }
        return@withContext 0
    }

    override suspend fun refresh(force: Boolean) = withContext(Dispatchers.IO) {
        _syncStatusFlow.value = SyncStatus.Syncing
        val targetUrl = getApiUrl()

        try {
            val request = Request.Builder()
                .url(targetUrl)
                .header("User-Agent", "Hatifi-Android-App/1.0")
                .header("Accept", "application/json")
                .build()

            httpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    throw IllegalStateException("HTTP ${response.code}: ${response.message}")
                }
                val bodyStr = response.body?.string() ?: throw IllegalStateException("Empty response body")
                val remotePhones = parsePhonesJson(bodyStr)

                if (remotePhones.isNotEmpty()) {
                    val entities = remotePhones.map { mapPhoneToEntity(it) }
                    phoneDao.insertAll(entities)
                    val now = System.currentTimeMillis()
                    setLastSyncTimestamp(now)

                    _syncStatusFlow.value = SyncStatus.Success(
                        timestamp = now,
                        count = phoneDao.getCount(),
                        isRemote = true
                    )
                    return@withContext
                } else {
                    throw IllegalStateException("Zero phones parsed from remote source")
                }
            }
        } catch (e: Exception) {
            Log.e("PhoneRepository", "Remote sync failed: ${e.message}, falling back to Room cached data.")
            seedInitialDataIfEmpty()
            val lastSync = getLastSyncTimestamp()
            _syncStatusFlow.value = SyncStatus.Fallback(
                message = e.localizedMessage ?: "Offline - using cached phone catalog",
                count = phoneDao.getCount(),
                timestamp = if (lastSync > 0) lastSync else System.currentTimeMillis()
            )
        }
    }

    private fun mapPhoneToEntity(phone: Phone): PhoneEntity {
        val prosArray = JSONArray()
        phone.pros.forEach { prosArray.put(JSONObject().put("en", it.en).put("ar", it.ar)) }

        val consArray = JSONArray()
        phone.cons.forEach { consArray.put(JSONObject().put("en", it.en).put("ar", it.ar)) }

        return PhoneEntity(
            id = phone.id,
            name = phone.name,
            brand = phone.brand,
            year = phone.releaseYear,
            priceUsd = phone.approxPriceUsd,
            category = phone.category.name,
            imageUrl = phone.imageUrl,
            display = phone.specs.display.let { "${it.size} ${it.type}, ${it.refreshRateHz}Hz, ${it.resolution}" },
            chipset = phone.specs.chipset.name,
            ram = phone.specs.memory.ramDisplay,
            storage = phone.specs.memory.storageDisplay,
            cameras = phone.specs.cameras.rearSummary,
            battery = "${phone.specs.battery.capacityMah}mAh, ${phone.specs.battery.wiredChargingWatts}W",
            os = phone.specs.os.osName,
            connectivity = if (phone.specs.connectivity.has5g) "5G, Wi-Fi, NFC" else "4G, Wi-Fi, NFC",
            waterResistance = phone.specs.build.waterResistance,
            weight = "${phone.specs.build.weightGrams}g",
            prosJson = prosArray.toString(),
            consJson = consArray.toString(),
            verdict = phone.verdict.status.name,
            verdictReasonEn = phone.verdict.reason.en,
            verdictReasonAr = phone.verdict.reason.ar,
            valueScore = phone.verdict.valueScore,
            isFavorite = false,
            isLatest = phone.isLatest,
            accentColorHex = phone.accentColorHex
        )
    }

    private fun mapEntityToPhone(entity: PhoneEntity): Phone {
        val cat = try {
            PhoneCategory.valueOf(entity.category.uppercase())
        } catch (e: Exception) {
            PhoneCategory.MID_RANGE
        }

        val verdictStatus = try {
            RecommendationStatus.valueOf(entity.verdict.uppercase())
        } catch (e: Exception) {
            RecommendationStatus.YES
        }

        val pros = parseLocalizedList(entity.prosJson) ?: listOf(
            LocalizedString("Great performance and reliability", "أداء ممتاز واعتمادية عالية")
        )
        val cons = parseLocalizedList(entity.consJson) ?: listOf(
            LocalizedString("Standard package features", "مواصفات قياسية")
        )

        // Find specs in seed data if matches id, or generate complete default specs
        val seedMatch = SeedPhonesData.phones.firstOrNull { it.id == entity.id }
        val specs = seedMatch?.specs ?: PhoneSpecs(
            display = DisplaySpecs("6.7\"", entity.display, 120, "1080 x 2400", 1500),
            chipset = ChipsetSpecs(entity.chipset, "Octa-core", "Adreno/Mali", "4nm", "High-End"),
            memory = MemorySpecs(listOf(entity.ram), listOf(entity.storage)),
            cameras = CameraSpecs(entity.cameras, "16MP", "4K@30fps", 50, true, false),
            battery = BatterySpecs(5000, 45, 0, false),
            os = OsSpecs(entity.os, entity.os, 4),
            connectivity = ConnectivitySpecs(true, "Wi-Fi 6", "5.3", true, false, "USB Type-C"),
            build = BuildSpecs(entity.waterResistance, "160 x 75 x 8 mm", 195, "Aluminum & Glass")
        )

        return Phone(
            id = entity.id,
            name = entity.name,
            brand = entity.brand,
            releaseYear = entity.year,
            approxPriceUsd = entity.priceUsd,
            category = cat,
            specs = specs,
            pros = pros,
            cons = cons,
            verdict = RecommendationVerdict(
                status = verdictStatus,
                reason = LocalizedString(entity.verdictReasonEn, entity.verdictReasonAr),
                valueScore = entity.valueScore
            ),
            imageUrl = entity.imageUrl,
            accentColorHex = entity.accentColorHex,
            isLatest = entity.isLatest,
            rating = 4.7f
        )
    }

    private fun parsePhonesJson(jsonStr: String): List<Phone> {
        val result = mutableListOf<Phone>()
        try {
            val array = if (jsonStr.trim().startsWith("[")) {
                JSONArray(jsonStr)
            } else {
                val root = JSONObject(jsonStr)
                root.optJSONArray("phones") ?: JSONArray()
            }

            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                val id = obj.optString("id", "phone-${System.currentTimeMillis()}-$i")
                val name = obj.getString("name")
                val brand = obj.optString("brand", "Generic")
                val year = obj.optInt("year", obj.optInt("releaseYear", 2024))
                val priceUsd = obj.optInt("priceUsd", obj.optInt("approxPriceUsd", 399))
                val catStr = obj.optString("category", "MID_RANGE")
                val cat = try { PhoneCategory.valueOf(catStr.uppercase()) } catch (e: Exception) { PhoneCategory.MID_RANGE }
                val imageUrl = obj.optString("imageUrl", null)

                // Match existing seed phone for deep specs if present, otherwise build specs
                val seed = SeedPhonesData.phones.firstOrNull { it.id == id }
                val fullSpecs = seed?.specs ?: PhoneSpecs(
                    display = DisplaySpecs(
                        size = "6.7\"",
                        type = obj.optString("display", "AMOLED 120Hz"),
                        refreshRateHz = 120,
                        resolution = "1080 x 2400",
                        peakBrightnessNits = 1600
                    ),
                    chipset = ChipsetSpecs(
                        name = obj.optString("chipset", "Octa-Core"),
                        cpu = "Octa-core",
                        gpu = "GPU",
                        processNode = "4nm",
                        scoreTier = "Balanced"
                    ),
                    memory = MemorySpecs(
                        ramOptions = listOf(obj.optString("ram", "8GB / 12GB")),
                        storageOptions = listOf(obj.optString("storage", "128GB / 256GB"))
                    ),
                    cameras = CameraSpecs(
                        rearSummary = obj.optString("cameras", "50MP Main (OIS)"),
                        frontSummary = "16MP",
                        videoRecording = "4K@30fps",
                        mainSensorMp = 50,
                        hasOis = true,
                        hasTelephoto = false
                    ),
                    battery = BatterySpecs(5000, 45, 0, false),
                    os = OsSpecs(obj.optString("os", "Android 15"), "UI Skin", 4),
                    connectivity = ConnectivitySpecs(true, "Wi-Fi 6", "5.4", true, false, "USB-C"),
                    build = BuildSpecs(obj.optString("waterResistance", "IP68"), "160 x 75 x 8 mm", 200, "Glass/Metal")
                )

                val verdict = if (obj.has("verdict")) {
                    val vStr = obj.optString("verdict", "YES")
                    val vStatus = try { RecommendationStatus.valueOf(vStr.uppercase()) } catch (e: Exception) { RecommendationStatus.YES }
                    val rEn = obj.optString("verdictReasonEn", "Recommended for its strong value.")
                    val rAr = obj.optString("verdictReasonAr", "نوصي به لتميزه في فئته السعرية.")
                    RecommendationVerdict(vStatus, LocalizedString(rEn, rAr), 8.8f)
                } else {
                    VerdictCalculator.calculateVerdict(priceUsd, cat, fullSpecs)
                }

                val pros = parseLocalizedList(obj.optString("pros")) ?: (seed?.pros ?: listOf(
                    LocalizedString("Great balance of performance and battery", "توازن ممتاز بين الأداء وعمر البطارية")
                ))

                val cons = parseLocalizedList(obj.optString("cons")) ?: (seed?.cons ?: listOf(
                    LocalizedString("Average charging speed", "سرعة شحن متوسطة")
                ))

                result.add(
                    Phone(
                        id = id,
                        name = name,
                        brand = brand,
                        releaseYear = year,
                        approxPriceUsd = priceUsd,
                        category = cat,
                        specs = fullSpecs,
                        pros = pros,
                        cons = cons,
                        verdict = verdict,
                        imageUrl = imageUrl,
                        accentColorHex = seed?.accentColorHex ?: "#2563EB",
                        isLatest = obj.optBoolean("isLatest", year >= 2024),
                        rating = 4.6f
                    )
                )
            }
        } catch (e: Exception) {
            Log.e("PhoneRepository", "Error parsing phones JSON", e)
        }
        return result
    }

    private fun parseLocalizedList(jsonOrStr: String): List<LocalizedString>? {
        if (jsonOrStr.isBlank()) return null
        return try {
            val arr = JSONArray(jsonOrStr)
            val list = mutableListOf<LocalizedString>()
            for (i in 0 until arr.length()) {
                val item = arr.get(i)
                if (item is JSONObject) {
                    list.add(LocalizedString(item.optString("en", ""), item.optString("ar", "")))
                } else {
                    val s = item.toString()
                    list.add(LocalizedString(s, s))
                }
            }
            list
        } catch (e: Exception) {
            null
        }
    }
}
