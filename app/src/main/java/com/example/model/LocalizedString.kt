package com.example.model

/**
 * Encapsulates text in English and Arabic for seamless bilingual rendering.
 */
data class LocalizedString(
    val en: String,
    val ar: String
) {
    fun get(isArabic: Boolean): String = if (isArabic) ar else en
}
