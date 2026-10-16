package com.example.model

enum class RecommendationStatus(
    val titleEn: String,
    val titleAr: String,
    val shortEn: String,
    val shortAr: String
) {
    YES(
        titleEn = "Yes - Recommended",
        titleAr = "نعم - نوصي به بشدة",
        shortEn = "Recommended",
        shortAr = "نوصي به"
    ),
    DEPENDS(
        titleEn = "Depends on use",
        titleAr = "يعتمد على الاستخدام",
        shortEn = "Conditional",
        shortAr = "حسب الاستخدام"
    ),
    NO(
        titleEn = "No - Not recommended",
        titleAr = "لا - لا نوصي به",
        shortEn = "Not Recommended",
        shortAr = "لا نوصي به"
    );

    fun getTitle(isArabic: Boolean): String = if (isArabic) titleAr else titleEn
    fun getShort(isArabic: Boolean): String = if (isArabic) shortAr else shortEn
}
