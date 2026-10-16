package com.example.model

data class Phone(
    val id: String,
    val name: String,
    val brand: String,
    val releaseYear: Int,
    val approxPriceUsd: Int,
    val category: PhoneCategory,
    val specs: PhoneSpecs,
    val pros: List<LocalizedString>,
    val cons: List<LocalizedString>,
    val verdict: RecommendationVerdict,
    val imageUrl: String? = null,
    val accentColorHex: String = "#0E7490",
    val isLatest: Boolean = false,
    val rating: Float = 4.5f
) {
    val formattedPrice: String get() = "$$approxPriceUsd"
    
    fun getLocalPriceApprox(currencySymbol: String = "USD"): String {
        return "$$approxPriceUsd (~${approxPriceUsd * 3.75} SAR)"
    }
}
