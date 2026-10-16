package com.example.util

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.text.NumberFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

enum class CurrencyMode(val labelEn: String, val labelAr: String) {
    BOTH("Both (USD & DZD)", "الاثنان (دولار ودينار)"),
    USD_ONLY("USD Only ($)", "بالدولار فقط ($)"),
    DZD_ONLY("DZD Only (د.ج)", "بالدينار الجزائري فقط (د.ج)");

    fun getLabel(isArabic: Boolean): String = if (isArabic) labelAr else labelEn
}

object CurrencyHelper {

    const val DEFAULT_DZD_RATE = 134.0
    private const val EXCHANGE_API_URL = "https://open.er-api.com/v6/latest/USD"

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    suspend fun fetchLiveDzdRate(): Double? = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url(EXCHANGE_API_URL)
                .header("Accept", "application/json")
                .build()

            httpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val body = response.body?.string() ?: return@withContext null
                    val json = JSONObject(body)
                    val rates = json.optJSONObject("rates")
                    val dzd = rates?.optDouble("DZD", DEFAULT_DZD_RATE)
                    if (dzd != null && dzd > 0) return@withContext dzd
                }
            }
        } catch (e: Exception) {
            // Log and return null to trigger fallback
        }
        null
    }

    fun formatPrice(
        priceUsd: Int,
        dzdRate: Double,
        mode: CurrencyMode,
        isArabic: Boolean
    ): String {
        val usdString = "$$priceUsd"
        val dzdAmount = Math.round(priceUsd * dzdRate)
        val numberFormat = NumberFormat.getNumberInstance(Locale.US)
        val formattedDzd = numberFormat.format(dzdAmount)
        val dzdString = if (isArabic) "$formattedDzd د.ج" else "$formattedDzd DZD"

        return when (mode) {
            CurrencyMode.USD_ONLY -> usdString
            CurrencyMode.DZD_ONLY -> dzdString
            CurrencyMode.BOTH -> if (isArabic) "$usdString • $dzdString" else "$usdString • $dzdString"
        }
    }
}
