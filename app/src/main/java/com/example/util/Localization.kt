package com.example.util

enum class AppLanguage(val code: String, val label: String) {
    ARABIC("ar", "العربية"),
    ENGLISH("en", "English")
}

object Strings {

    fun appName(isAr: Boolean) = if (isAr) "هاتفي" else "Hatifi"
    fun appTagline(isAr: Boolean) = if (isAr) 
        "دليلك الذكي لمواصفات الهواتف ومقارنتها بدقة" 
    else 
        "Smart Phone Database & Side-by-Side Specs Comparison"

    fun searchPlaceholder(isAr: Boolean) = if (isAr) 
        "ابحث باسم الهاتف أو الماركة (سامسونج، آيفون...)" 
    else 
        "Search phone name or brand (e.g. S25, Pixel)..."

    // Tabs
    fun tabAll(isAr: Boolean) = if (isAr) "جميع الهواتف" else "All Phones"
    fun tabLatest(isAr: Boolean) = if (isAr) "أحدث الإصدارات" else "Latest Releases"
    fun tabCompare(isAr: Boolean) = if (isAr) "المقارنة" else "Compare"

    // Filters
    fun filterBrand(isAr: Boolean) = if (isAr) "الماركة" else "Brand"
    fun filterCategory(isAr: Boolean) = if (isAr) "الفئة" else "Category"
    fun filterPrice(isAr: Boolean) = if (isAr) "نطاق السعر" else "Price Range"
    fun filterYear(isAr: Boolean) = if (isAr) "سنة الإصدار" else "Release Year"
    fun filterVerdict(isAr: Boolean) = if (isAr) "الرأي والتوصية" else "Recommendation"
    fun filterAll(isAr: Boolean) = if (isAr) "الكل" else "All"
    fun filterReset(isAr: Boolean) = if (isAr) "إعادة ضبط" else "Reset"
    fun filterApply(isAr: Boolean) = if (isAr) "تطبيق الفلاتر" else "Apply Filters"
    fun filterTitle(isAr: Boolean) = if (isAr) "تصفية الهواتف" else "Filter Phones"

    // Price brackets
    fun priceAny(isAr: Boolean) = if (isAr) "أي سعر" else "Any Price"
    fun priceUnder200(isAr: Boolean) = if (isAr) "أقل من $200" else "Under $200"
    fun price200to500(isAr: Boolean) = if (isAr) "$200 - $500" else "$200 - $500"
    fun price500to900(isAr: Boolean) = if (isAr) "$500 - $900" else "$500 - $900"
    fun priceOver900(isAr: Boolean) = if (isAr) "أكثر من $900" else "Over $900"

    // Verdict labels
    fun verdictRecommended(isAr: Boolean) = if (isAr) "نوصي به" else "Recommended"
    fun verdictDepends(isAr: Boolean) = if (isAr) "حسب الاستخدام" else "Conditional"
    fun verdictNotRecommended(isAr: Boolean) = if (isAr) "لا نوصي به" else "Not Recommended"
    fun verdictQuestion(isAr: Boolean) = if (isAr) "هل نوصي بشرائه؟" else "Do we recommend it?"
    fun verdictAnalysis(isAr: Boolean) = if (isAr) "تحليل القيمة مقابل السعر" else "Value for Money Analysis"
    fun scoreOutOfTen(isAr: Boolean) = if (isAr) "تقييم القيمة: " else "Value Score: "

    // Specs Headers
    fun specsSection(isAr: Boolean) = if (isAr) "المواصفات التقنية الكاملة" else "Full Technical Specifications"
    fun specDisplay(isAr: Boolean) = if (isAr) "الشاشة والعرض" else "Display & Screen"
    fun specChipset(isAr: Boolean) = if (isAr) "المعالج والأداء" else "Chipset & Performance"
    fun specMemory(isAr: Boolean) = if (isAr) "الذاكرة والتخزين" else "RAM & Storage"
    fun specCameras(isAr: Boolean) = if (isAr) "منظومة الكاميرات" else "Camera System"
    fun specBattery(isAr: Boolean) = if (isAr) "البطارية والشحن" else "Battery & Charging"
    fun specOs(isAr: Boolean) = if (isAr) "نظام التشغيل والدعم" else "OS & Software Support"
    fun specConnectivity(isAr: Boolean) = if (isAr) "الاتصال والشبكات" else "Connectivity"
    fun specBuild(isAr: Boolean) = if (isAr) "المتانة والمقاومة" else "Build & Durability"

    // Pros & Cons
    fun prosTitle(isAr: Boolean) = if (isAr) "المميزات الإيجابية" else "Pros & Highlights"
    fun consTitle(isAr: Boolean) = if (isAr) "العيوب والمآخذ" else "Cons & Drawbacks"

    // Compare
    fun compareUpTo3(isAr: Boolean) = if (isAr) "قارن حتى 3 هواتف جنباً إلى جنب" else "Compare up to 3 phones side-by-side"
    fun addToCompare(isAr: Boolean) = if (isAr) "إضافة للمقارنة" else "Add to Compare"
    fun removeFromCompare(isAr: Boolean) = if (isAr) "إزالة" else "Remove"
    fun compareNow(isAr: Boolean, count: Int) = if (isAr) "مقارنة ($count هواتف)" else "Compare ($count phones)"
    fun selectPhonesToCompare(isAr: Boolean) = if (isAr) "اختر هاتفين أو ثلاثة للمقارنة الفورية" else "Select 2 or 3 phones to compare"
    fun clearComparison(isAr: Boolean) = if (isAr) "إفراغ المقارنة" else "Clear All"
    fun winnerHighlight(isAr: Boolean) = if (isAr) "الميزة الأفضل" else "Top Spec"

    // TikTok Owner Promo
    fun tiktokFollow(isAr: Boolean) = if (isAr) "تابعنا على تيك توك @az.techno4" else "Follow us on TikTok @az.techno4"
    fun tiktokSubtitle(isAr: Boolean) = if (isAr) 
        "أحدث تجارب الهواتف، مقارنات السرعة، وأقوى العروض!" 
    else 
        "Daily phone reviews, speed benchmarks, and honest buying tips!"
    fun tiktokButton(isAr: Boolean) = if (isAr) "متابعة الحساب" else "Follow @az.techno4"
    fun tiktokFooter(isAr: Boolean) = if (isAr) "هاتفي • حساب المالك @az.techno4 على تيك توك" else "Hatifi • Owner Account @az.techno4 on TikTok"

    // Data Source & Sync
    fun dataSourceConfig(isAr: Boolean) = if (isAr) "إعدادات مزامنة البيانات" else "Data Source & Sync"
    fun syncNow(isAr: Boolean) = if (isAr) "تحديث الآن" else "Sync Now"
    fun syncing(isAr: Boolean) = if (isAr) "جارٍ جلب أحدث الهواتف..." else "Fetching latest phones..."
    fun syncSuccess(isAr: Boolean) = if (isAr) "تم التحديث بنجاح" else "Data updated successfully"
    fun syncFallback(isAr: Boolean) = if (isAr) "وضع عدم الاتصال (بيانات مخزنة)" else "Offline Cache Mode"
    fun apiUrlLabel(isAr: Boolean) = if (isAr) "رابط مصدر البيانات (API URL)" else "Data Source API URL"
    fun apiUrlHint(isAr: Boolean) = if (isAr) "يمكنك تغيير الرابط لأي مصدر JSON متوافق" else "Easily point to any custom JSON phone endpoint"
    fun autoRefreshNote(isAr: Boolean) = if (isAr) 
        "يتم التحديث تلقائياً عند فتح التطبيق وكل 24 ساعة، مع حساب التوصيات تلقائياً للهواتف الجديدة."
    else 
        "Refreshes automatically on launch and every 24 hours, auto-calculating recommendations for new models."

    // Empty state
    fun noResults(isAr: Boolean) = if (isAr) "لم يتم العثور على هواتف مطابقة" else "No matching phones found"
    fun noResultsTip(isAr: Boolean) = if (isAr) "جرّب تغيير كلمات البحث أو تخفيف الفلاتر" else "Try adjusting your search terms or filters"
    fun foundPhones(isAr: Boolean, count: Int) = if (isAr) "$count هاتف متوفر" else "$count phones found"
}
