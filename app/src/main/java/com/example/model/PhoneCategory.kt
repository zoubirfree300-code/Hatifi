package com.example.model

enum class PhoneCategory(
    val titleEn: String,
    val titleAr: String
) {
    ALL("All", "الكل"),
    FLAGSHIP("Flagship", "رائد / فلاجشيب"),
    MID_RANGE("Mid-Range", "الفئة المتوسطة"),
    BUDGET("Budget", "الفئة الاقتصادية"),
    GAMING("Gaming", "هواتف الألعاب"),
    CAMERA("Camera-Focused", "موجّه للتصوير");

    fun getTitle(isArabic: Boolean): String = if (isArabic) titleAr else titleEn
}
