package com.example.data

import com.example.model.*

object SeedPhonesData {

    val phones: List<Phone> = listOf(
        Phone(
            id = "samsung-s25-ultra",
            name = "Samsung Galaxy S25 Ultra",
            brand = "Samsung",
            releaseYear = 2025,
            approxPriceUsd = 1299,
            category = PhoneCategory.FLAGSHIP,
            isLatest = true,
            accentColorHex = "#2563EB",
            rating = 4.9f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.9\"",
                    type = "Dynamic LTPO AMOLED 2X, 120Hz, HDR10+",
                    refreshRateHz = 120,
                    resolution = "1440 x 3120 pixels (~500 ppi)",
                    peakBrightnessNits = 2600
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Elite for Galaxy",
                    cpu = "Octa-core (2x4.47 GHz Oryon + 6x3.53 GHz)",
                    gpu = "Adreno 830",
                    processNode = "3nm",
                    scoreTier = "Top Tier Ultra (AnTuTu 2.8M+)"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB", "1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "200 MP (wide, OIS) + 50 MP (5x periscope, OIS) + 50 MP (3x telephoto) + 50 MP (ultrawide)",
                    frontSummary = "12 MP (wide, Dual Pixel PDAF)",
                    videoRecording = "8K@30fps, 4K@120fps, 10-bit HDR",
                    mainSensorMp = 200,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 45,
                    wirelessChargingWatts = 15,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "One UI 7",
                    supportYears = 7
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = true,
                    usbType = "USB Type-C 3.2, DisplayPort"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 (up to 1.5m for 30 min)",
                    dimensionsMm = "162.8 x 77.6 x 8.2 mm",
                    weightGrams = 219,
                    materials = "Titanium frame (grade 5), Gorilla Armor glass front & back"
                )
            ),
            pros = listOf(
                LocalizedString("Unrivaled anti-reflective Gorilla Armor flat display", "شاشة مسطحة خارقة مضادة للانعكاس بزجاج غوريلا أرمور"),
                LocalizedString("Integrated S-Pen stylus with Bluetooth productivity", "قلم S-Pen مدمج مع دعم البلوتوث والإنتاجية الكاملة"),
                LocalizedString("7 years of guaranteed OS updates and security patches", "7 سنوات كاملة من تحديثات النظام والأمان المضمونة"),
                LocalizedString("Best-in-class zoom capabilities with dual telephoto", "أفضل تقريب بصري في فئته بفضل الكاميرات المزدوجة المقربة")
            ),
            cons = listOf(
                LocalizedString("Very high entry price point", "سعر مرتفع جداً عند الإطلاق"),
                LocalizedString("Wired charging still limited to 45W compared to rivals", "سرعة الشحن السلكي ما زالت 45 واط متأخرة عن المنافسين"),
                LocalizedString("No charger included in the retail box", "لا يتوفر شاحن في علبة الهاتف")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "The ultimate Android powerhouse. If budget permits, it delivers unmatched durability, camera versatility, and 7 years of support.",
                    ar = "الهاتف الأقوى بدون منازع في عالم أندرويد. إن كانت ميزانيتك تسمح فهو يقدم أفضل شاشة، كاميرات جبارة، ودعم طويل لمدة 7 سنوات."
                ),
                valueScore = 9.3f
            )
        ),

        Phone(
            id = "apple-iphone-16-pro-max",
            name = "Apple iPhone 16 Pro Max",
            brand = "Apple",
            releaseYear = 2024,
            approxPriceUsd = 1199,
            category = PhoneCategory.FLAGSHIP,
            isLatest = true,
            accentColorHex = "#6366F1",
            rating = 4.9f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.9\"",
                    type = "LTPO Super Retina XDR OLED, 120Hz ProMotion",
                    refreshRateHz = 120,
                    resolution = "1320 x 2868 pixels (~460 ppi)",
                    peakBrightnessNits = 2000
                ),
                chipset = ChipsetSpecs(
                    name = "Apple A18 Pro",
                    cpu = "6-core (2 performance + 4 efficiency)",
                    gpu = "Apple 6-core GPU, Hardware Ray Tracing",
                    processNode = "3nm (second gen)",
                    scoreTier = "Top Tier Benchmark Leader"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB"),
                    storageOptions = listOf("256GB", "512GB", "1TB NVMe")
                ),
                cameras = CameraSpecs(
                    rearSummary = "48 MP Fusion (OIS) + 12 MP (5x tetraprism telephoto) + 48 MP (ultrawide)",
                    frontSummary = "12 MP TrueDepth with autofocus",
                    videoRecording = "4K@120fps Dolby Vision, ProRes Log, Audio Mix",
                    mainSensorMp = 48,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 4685,
                    wiredChargingWatts = 30,
                    wirelessChargingWatts = 25,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "iOS 18",
                    uiSkin = "iOS (with Apple Intelligence)",
                    supportYears = 6
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = true,
                    usbType = "USB Type-C 3.2 Gen 2 (up to 10Gbps)"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 (6m for 30 min)",
                    dimensionsMm = "163.0 x 77.6 x 8.25 mm",
                    weightGrams = 227,
                    materials = "Grade 5 Titanium frame, Ceramic Shield front, textured matte glass"
                )
            ),
            pros = listOf(
                LocalizedString("Tremendous battery stamina, best in iPhone history", "عمر بطارية خارق هو الأطول في تاريخ هواتف آيفون"),
                LocalizedString("Studio-grade 4K 120fps video recording and Audio Mix", "تصوير فيديو احترافي 4K بمعدل 120 إطاراً مع ميزة Audio Mix"),
                LocalizedString("Dedicated Camera Control haptic tactile key", "زر التحكم بالكاميرا الميكانيكي واللمسي السريع"),
                LocalizedString("Class-leading performance and thermal management", "أداء استثنائي وإدارة حرارية ممتازة بفضل معالج A18 Pro")
            ),
            cons = listOf(
                LocalizedString("Camera Control button has a steep learning curve", "زر الكاميرا يحتاج لوقت للتعود على حساسيته"),
                LocalizedString("Fast charging is still relatively modest (~30W)", "سرعة الشحن متواضعة مقارنة بمنافسي أندرويد الصينيين"),
                LocalizedString("No standard 3.5mm jack or expandable memory", "لا يوجد منفذ سماعات أو بطاقة ذاكرة خارجية")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "The gold standard for content creators and iOS loyalists. Incredible battery endurance and unmatched video recording quality.",
                    ar = "الخيار المثالي لصناع المحتوى ومحبي آبل. بطارية تدوم طويلاً مع جودة فيديو استثنائية لا تُقارن."
                ),
                valueScore = 9.2f
            )
        ),

        Phone(
            id = "google-pixel-9-pro",
            name = "Google Pixel 9 Pro",
            brand = "Google",
            releaseYear = 2024,
            approxPriceUsd = 999,
            category = PhoneCategory.CAMERA,
            isLatest = true,
            accentColorHex = "#10B981",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.3\"",
                    type = "Super Actua LTPO OLED, 120Hz",
                    refreshRateHz = 120,
                    resolution = "1280 x 2856 pixels (~495 ppi)",
                    peakBrightnessNits = 3000
                ),
                chipset = ChipsetSpecs(
                    name = "Google Tensor G4",
                    cpu = "Octa-core (1x3.1 GHz Cortex-X4 + 3x2.6 GHz + 4x1.92 GHz)",
                    gpu = "Mali-G715 MC7",
                    processNode = "4nm",
                    scoreTier = "High-End AI Specialist"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("16GB"),
                    storageOptions = listOf("128GB", "256GB", "512GB", "1TB")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, OIS) + 48 MP (5x telephoto, OIS) + 48 MP (ultrawide, macro)",
                    frontSummary = "42 MP (ultrawide, autofocus)",
                    videoRecording = "8K@30fps (via Video Boost), 4K@60fps HDR",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 4700,
                    wiredChargingWatts = 27,
                    wirelessChargingWatts = 21,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "Pixel UI (Gemini Nano on-device)",
                    supportYears = 7
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = true,
                    usbType = "USB Type-C 3.2"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 (1.5m for 30 min)",
                    dimensionsMm = "152.8 x 72.0 x 8.5 mm",
                    weightGrams = 199,
                    materials = "Polished aluminum frame, Gorilla Glass Victus 2"
                )
            ),
            pros = listOf(
                LocalizedString("Pro camera features in a compact, hand-friendly 6.3\" body", "مواصفات كاميرا احترافية في حجم مدمج ومريح لليد 6.3 إنش"),
                LocalizedString("Market-leading still photography and computational skin tones", "أفضل معالجة صور فوتوغرافية وألوان بشرة طبيعية في السوق"),
                LocalizedString("Generous 16GB RAM standard across all models", "ذاكرة عشوائية 16 جيجابايت قياسية في جميع النسخ"),
                LocalizedString("7 years of monthly Feature Drops and OS upgrades", "7 سنوات كاملة من التحديثات المستمرة وميزات بكسل الحصرية")
            ),
            cons = listOf(
                LocalizedString("Tensor G4 falls behind Snapdragon in heavy 3D gaming", "معالج تنسور أقل أداءً من سنابدراجون في الألعاب الثقيلة"),
                LocalizedString("Base storage is only 128GB on a $1000 phone", "سعة التخزين الأساسية 128 جيجابايت فقط بسعر 1000 دولار"),
                LocalizedString("Wired charging is capped at 27W", "سرعة الشحن السلكي محدودة بـ 27 واط")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "The pinnacle of mobile photography and clean software in a compact form factor. Highly recommended for photo enthusiasts.",
                    ar = "قمة التصوير الفوتوغرافي ونقاء نظام أندرويد في حجم أنيق ومدمج. نوصي به بشدة لعشاق التصوير."
                ),
                valueScore = 9.0f
            )
        ),

        Phone(
            id = "xiaomi-15-ultra",
            name = "Xiaomi 15 Ultra",
            brand = "Xiaomi",
            releaseYear = 2025,
            approxPriceUsd = 1099,
            category = PhoneCategory.CAMERA,
            isLatest = true,
            accentColorHex = "#F97316",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.73\"",
                    type = "LTPO AMOLED, 1B colors, 120Hz, Dolby Vision",
                    refreshRateHz = 120,
                    resolution = "1440 x 3200 pixels (~522 ppi)",
                    peakBrightnessNits = 3200
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Elite",
                    cpu = "Octa-core 4.32 GHz Oryon",
                    gpu = "Adreno 830",
                    processNode = "3nm",
                    scoreTier = "Flagship Beast"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB", "1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP 1-inch main (OIS) + 200 MP (4.3x periscope, OIS) + 50 MP (3x tele) + 50 MP (ultrawide) Leica Optics",
                    frontSummary = "32 MP (wide, HDR)",
                    videoRecording = "8K@30fps, 4K@120fps 10-bit Dolby Vision",
                    mainSensorMp = 200,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 6000,
                    wiredChargingWatts = 90,
                    wirelessChargingWatts = 80,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "HyperOS 2.0",
                    supportYears = 5
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.2 Gen 2"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68/IP69 dust/water resistant",
                    dimensionsMm = "161.4 x 75.3 x 9.2 mm",
                    weightGrams = 226,
                    materials = "Titanium/Aluminum frame, Vegan leather or ceramic back"
                )
            ),
            pros = listOf(
                LocalizedString("Monster 200MP periscope zoom combined with 1-inch main sensor", "كاميرا زوم خارقة بدقة 200 ميجابكسل مع مستشعر رئيسي عملاق 1 إنش"),
                LocalizedString("Huge 6000mAh silicon-carbon battery with 90W fast charging", "بطارية سيليكون كربون ضخمة 6000 مللي أمبير مع شحن 90 واط"),
                LocalizedString("IP69 certification for extreme water & high pressure protection", "معيار IP69 لمقاومة الماء وضغط الرذاذ العالي"),
                LocalizedString("True optical Leica color science modes", "ألوان ومعالجة لايكا البصرية الأسطورية")
            ),
            cons = listOf(
                LocalizedString("Large circular camera bump makes it top-heavy", "بروز الكاميرات الدائري كبير ويزيد من ثقل الجزء العلوي"),
                LocalizedString("HyperOS still has occasional notification delays", "واجهة هايبر أو إس قد تواجه أحياناً تأخيراً طفيفاً في التنبيهات")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "A photographic monster. The combo of a 1-inch main sensor and 200MP periscope makes it the absolute camera champ.",
                    ar = "وحش تصوير حقيقي. الجمع بين مستشعر 1 إنش وكاميرا زوم 200 ميجابكسل يجعله بطل الكاميرات بلا منازع."
                ),
                valueScore = 9.4f
            )
        ),

        Phone(
            id = "oneplus-13",
            name = "OnePlus 13",
            brand = "OnePlus",
            releaseYear = 2024,
            approxPriceUsd = 899,
            category = PhoneCategory.FLAGSHIP,
            isLatest = true,
            accentColorHex = "#EF4444",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.82\"",
                    type = "LTPO 4.0 AMOLED, 120Hz, Dolby Vision, 4500 nits peak",
                    refreshRateHz = 120,
                    resolution = "1440 x 3168 pixels (~510 ppi)",
                    peakBrightnessNits = 4500
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Elite",
                    cpu = "Octa-core 4.32 GHz Oryon",
                    gpu = "Adreno 830",
                    processNode = "3nm",
                    scoreTier = "Flagship Tier 1"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB", "24GB"),
                    storageOptions = listOf("256GB", "512GB", "1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Sony LYT-808 (OIS) + 50 MP (3x periscope telephoto) + 50 MP (ultrawide) Hasselblad",
                    frontSummary = "32 MP (wide)",
                    videoRecording = "8K@30fps, 4K@60fps Dolby Vision",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 6000,
                    wiredChargingWatts = 100,
                    wirelessChargingWatts = 50,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "OxygenOS 15",
                    supportYears = 5
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.2 Gen 1"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 & IP69 water/dust resistant",
                    dimensionsMm = "162.9 x 76.5 x 8.5 mm",
                    weightGrams = 213,
                    materials = "Aluminum frame, Ceramic/Glass back"
                )
            ),
            pros = listOf(
                LocalizedString("Giant 6000mAh battery with blazing 100W wired and 50W wireless", "بطارية ضخمة 6000 مللي أمبير مع شحن فائق 100 واط و50 واط لاسلكي"),
                LocalizedString("DisplayMate A++ quad-curved micro-bezel screen", "شاشة مذهلة بأطراف ميكرو دقيقة وسطوع قياسي"),
                LocalizedString("Fluid OxygenOS 15 with parallel processing animations", "سلاسة فائقة في واجهة OxygenOS 15 الجديدة"),
                LocalizedString("Lower price than Samsung and Apple flagships", "سعر منافس وأقل بوضوح من رائدات سامسونج وآبل")
            ),
            cons = listOf(
                LocalizedString("Periscope optical zoom capped at 3x (relies on crop for 10x)", "التقريب البصري محدود بـ 3x مقارنة بـ 5x عند المنافسين"),
                LocalizedString("No physical notification Alert Slider in some region editions", "تغيير مكان زر التنبيهات المنزلق")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Outstanding flagship value. You get 6000mAh battery, 100W charging, and Snapdragon 8 Elite for hundreds less than rivals.",
                    ar = "قيمة رائدة استثنائية. بطارية 6000 مللي أمبير مع شحن 100 واط وأقوى معالج بسعر يقل بمئات الدولارات عن منافسيه."
                ),
                valueScore = 9.5f
            )
        ),

        Phone(
            id = "samsung-galaxy-a55",
            name = "Samsung Galaxy A55 5G",
            brand = "Samsung",
            releaseYear = 2024,
            approxPriceUsd = 449,
            category = PhoneCategory.MID_RANGE,
            isLatest = false,
            accentColorHex = "#3B82F6",
            rating = 4.6f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.6\"",
                    type = "Super AMOLED, 120Hz, HDR10+",
                    refreshRateHz = 120,
                    resolution = "1080 x 2340 pixels (~390 ppi)",
                    peakBrightnessNits = 1000
                ),
                chipset = ChipsetSpecs(
                    name = "Exynos 1480 (AMD Xclipse 530 GPU)",
                    cpu = "Octa-core (4x2.75 GHz Cortex-A78 + 4x2.0 GHz)",
                    gpu = "Xclipse 530 (AMD RDNA2)",
                    processNode = "4nm",
                    scoreTier = "Mid-Range Upper Tier"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB", "12GB"),
                    storageOptions = listOf("128GB", "256GB (microSD up to 1TB)")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, OIS) + 12 MP (ultrawide) + 5 MP (macro)",
                    frontSummary = "32 MP (wide)",
                    videoRecording = "4K@30fps, 1080p@60fps with gyro-EIS",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 25,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (Upgradable to Android 15)",
                    uiSkin = "One UI 6.1 / 7",
                    supportYears = 4
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP67 dust/water resistant (1m for 30 min)",
                    dimensionsMm = "161.1 x 77.4 x 8.2 mm",
                    weightGrams = 213,
                    materials = "Aluminum metal frame, Gorilla Glass Victus+ front & back"
                )
            ),
            pros = listOf(
                LocalizedString("Premium metal frame and Gorilla Glass Victus+ build", "تصميم فخم بإطار معدني وزجاج غوريلا فيكتوس بلس"),
                LocalizedString("Full IP67 water and dust resistance", "مقاومة كاملة للماء والغبار بمعيار IP67"),
                LocalizedString("MicroSD card expansion slot support", "دعم منفذ بطاقة الذاكرة الخارجية كارت ميموري"),
                LocalizedString("Guaranteed 4 major OS upgrades and 5 years security", "4 تحديثات أندرويد رئيسية و5 سنوات أمان")
            ),
            cons = listOf(
                LocalizedString("Charging speed limited to modest 25W", "سرعة الشحن متواضعة 25 واط فقط"),
                LocalizedString("Bezels around the screen are slightly thick", "حواف الشاشة عريضة نسبياً مقارنة بالمنافسين"),
                LocalizedString("No telephoto zoom camera", "لا توجد كاميرا تقريب مخصصة")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "The safest mid-range purchase on the market. Metal build, IP67 rating, great display, and long software support.",
                    ar = "أضمن خيار للشراء في الفئة المتوسطة. بناء معدني، ومقاومة ماء IP67، وشاشة ممتازة ودعم طويل ومستقر."
                ),
                valueScore = 8.8f
            )
        ),

        Phone(
            id = "xiaomi-redmi-note-14-pro-plus",
            name = "Xiaomi Redmi Note 14 Pro+",
            brand = "Xiaomi",
            releaseYear = 2024,
            approxPriceUsd = 349,
            category = PhoneCategory.MID_RANGE,
            isLatest = true,
            accentColorHex = "#8B5CF6",
            rating = 4.7f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.67\"",
                    type = "1.5K Curved AMOLED, 120Hz, Dolby Vision, 3000 nits",
                    refreshRateHz = 120,
                    resolution = "1220 x 2712 pixels (~446 ppi)",
                    peakBrightnessNits = 3000
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 7s Gen 3",
                    cpu = "Octa-core 2.5 GHz",
                    gpu = "Adreno 710",
                    processNode = "4nm",
                    scoreTier = "Balanced Mid-Range"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB UFS 3.1")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Light Hunter 800 (OIS) + 50 MP (2.5x telephoto) + 8 MP (ultrawide)",
                    frontSummary = "20 MP (wide)",
                    videoRecording = "4K@30fps, 1080p@60fps",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 6200,
                    wiredChargingWatts = 90,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (HyperOS)",
                    uiSkin = "HyperOS",
                    supportYears = 3
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68/IP69K extreme dust and water resistance",
                    dimensionsMm = "162.5 x 74.7 x 8.7 mm",
                    weightGrams = 210,
                    materials = "Gorilla Glass Victus 2 front, reinforced shock-resistant chassis"
                )
            ),
            pros = listOf(
                LocalizedString("Massive 6200mAh battery with rapid 90W charging in the box", "بطارية عملاقة 6200 مللي أمبير مع شاحن 90 واط بالعلبة"),
                LocalizedString("Flagship-level IP68 and IP69K rugged water/pressure ratings", "مقاومة ماء وضغط عالي بمعياري IP68 و IP69K"),
                LocalizedString("Dedicated 2.5x optical telephoto camera in a mid-ranger", "كاميرا زوم حقيقية 2.5x في هاتف فئة متوسطة"),
                LocalizedString("Gorgeous 1.5K 120Hz curved AMOLED panel", "شاشة منحنية رائعة بدقة 1.5K وتردد 120 هرتز")
            ),
            cons = listOf(
                LocalizedString("Snapdragon 7s Gen 3 is fine for daily use but not a gaming chip", "المعالج ممتاز للاستخدام اليومي لكنه ليس مخصصاً للألعاب القوية"),
                LocalizedString("Some pre-installed bloatware apps", "وجود بعض التطبيقات الإضافية المثبتة مسبقاً")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Sensational specs for under $350. 6200mAh battery, IP69 rating, and real optical zoom make it an unbeatable mid-range deal.",
                    ar = "مواصفات مذهلة بأقل من 350 دولاراً. بطارية 6200 مللي أمبير، ومقاومة IP69 وزوم بصري تجعله صفقة لا تقهر."
                ),
                valueScore = 9.6f
            )
        ),

        Phone(
            id = "realme-gt-7-pro",
            name = "Realme GT 7 Pro",
            brand = "Realme",
            releaseYear = 2024,
            approxPriceUsd = 649,
            category = PhoneCategory.GAMING,
            isLatest = true,
            accentColorHex = "#EAB308",
            rating = 4.7f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.78\"",
                    type = "Eco2 OLED Plus (Samsung), 120Hz, Dolby Vision, 6000 nits peak",
                    refreshRateHz = 120,
                    resolution = "1264 x 2780 pixels (~450 ppi)",
                    peakBrightnessNits = 6000
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Elite",
                    cpu = "Octa-core 4.32 GHz Oryon",
                    gpu = "Adreno 830",
                    processNode = "3nm",
                    scoreTier = "Flagship Gaming Monster"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB, 1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Sony IMX906 (OIS) + 50 MP (3x periscope telephoto) + 8 MP (ultrawide)",
                    frontSummary = "16 MP (wide)",
                    videoRecording = "8K@24fps, 4K@60fps underwater photography mode",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 6500,
                    wiredChargingWatts = 120,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "Realme UI 6.0",
                    supportYears = 3
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68/IP69 with underwater shooting mode",
                    dimensionsMm = "162.5 x 76.9 x 8.6 mm",
                    weightGrams = 222,
                    materials = "Aviation aluminum frame, Mars Spacecraft textured glass back"
                )
            ),
            pros = listOf(
                LocalizedString("Cheapest phone on the globe with Snapdragon 8 Elite", "أرخص هاتف في العالم بمعالج سنابدراجون 8 إيليت الخارق"),
                LocalizedString("Gigantic 6500mAh Titan battery with ultra-fast 120W charging", "بطارية تايتان عملاقة 6500 مللي أمبير مع شاحن 120 واط"),
                LocalizedString("Record-shattering 6000 nits peak display brightness", "سطوع شاشة غير مسبوق يصل إلى 6000 شمعة"),
                LocalizedString("Underwater photography mode enabled by IP69 rating", "وضع تصوير مخصص تحت الماء بفضل معيار IP69")
            ),
            cons = listOf(
                LocalizedString("No wireless charging support", "لا يدعم الشحن اللاسلكي"),
                LocalizedString("USB port is older Type-C 2.0 standard", "منفذ التوصيل بسرعات USB 2.0 فقط"),
                LocalizedString("Ultrawide camera is basic 8MP sensor", "كاميرا الزاوية العريضة 8 ميجابكسل متواضعة")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "An insane performance bargain! If raw gaming power and battery life are your priorities, nothing touches this at $650.",
                    ar = "صفقة أداء خرافية! إن كانت أولويتك قوة الألعاب وعمر البطارية الخارق، فلا يوجد منافس يضاهيه بهذا السعر."
                ),
                valueScore = 9.6f
            )
        ),

        Phone(
            id = "apple-iphone-16",
            name = "Apple iPhone 16",
            brand = "Apple",
            releaseYear = 2024,
            approxPriceUsd = 799,
            category = PhoneCategory.FLAGSHIP,
            isLatest = true,
            accentColorHex = "#EC4899",
            rating = 4.5f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.1\"",
                    type = "Super Retina XDR OLED, 60Hz",
                    refreshRateHz = 60,
                    resolution = "1179 x 2556 pixels (~460 ppi)",
                    peakBrightnessNits = 2000
                ),
                chipset = ChipsetSpecs(
                    name = "Apple A18 (with Apple Intelligence)",
                    cpu = "6-core (2 performance + 4 efficiency)",
                    gpu = "Apple 5-core GPU",
                    processNode = "3nm",
                    scoreTier = "High-End Flagship"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB"),
                    storageOptions = listOf("128GB", "256GB", "512GB NVMe")
                ),
                cameras = CameraSpecs(
                    rearSummary = "48 MP Fusion (OIS, 2x in-sensor zoom) + 12 MP (ultrawide, macro)",
                    frontSummary = "12 MP TrueDepth with autofocus",
                    videoRecording = "4K@60fps Dolby Vision, Spatial Video & Audio",
                    mainSensorMp = 48,
                    hasOis = true,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 3561,
                    wiredChargingWatts = 25,
                    wirelessChargingWatts = 25,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "iOS 18",
                    uiSkin = "iOS (with Apple Intelligence)",
                    supportYears = 6
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = true,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 (6m for 30 min)",
                    dimensionsMm = "147.6 x 71.6 x 7.8 mm",
                    weightGrams = 170,
                    materials = "Aerospace-grade aluminum, color-infused back glass"
                )
            ),
            pros = listOf(
                LocalizedString("Action Button and Camera Control button both included", "تضمين زر الإجراءات وزر التحكم بالكاميرا الجديدين"),
                LocalizedString("Upgraded to 8GB RAM to support full Apple Intelligence", "ترقية الذاكرة إلى 8 جيجابايت لدعم ميزات الذكاء الاصطناعي"),
                LocalizedString("Feather-light 170g weight and superb build quality", "وزن خفيف جداً 170 غرام وجودة تصنيع راقية"),
                LocalizedString("Macro photography and spatial capture support", "دعم التصوير الماكرو والصور المجسمة")
            ),
            cons = listOf(
                LocalizedString("Display is inexcusably locked at 60Hz on an $800 phone", "الشاشة بتردد 60 هرتز قديم وغير مبرر بسعر 800 دولار"),
                LocalizedString("No dedicated telephoto optical zoom lens", "لا توجد عدسة تقريب بصري مخصصة"),
                LocalizedString("Modest battery charging speed", "سرعة شحن عادية لا تتجاوز 25 واط")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.DEPENDS,
                reason = LocalizedString(
                    en = "Depends on your tolerance for 60Hz. Great build, compact feel, and Apple Intelligence, but 60Hz in 2024 at $800 is a significant drawback.",
                    ar = "يعتمد على تقبلك لتردد 60 هرتز. هاتف ممتاز ومدمج ويدعم الذكاء الاصطناعي، لكن شاشة 60 هرتز بسعر 800 دولار عيب كبير."
                ),
                valueScore = 7.4f
            )
        ),

        Phone(
            id = "google-pixel-8a",
            name = "Google Pixel 8a",
            brand = "Google",
            releaseYear = 2024,
            approxPriceUsd = 499,
            category = PhoneCategory.MID_RANGE,
            isLatest = false,
            accentColorHex = "#14B8A6",
            rating = 4.6f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.1\"",
                    type = "Actua OLED, 120Hz, HDR",
                    refreshRateHz = 120,
                    resolution = "1080 x 2400 pixels (~430 ppi)",
                    peakBrightnessNits = 2000
                ),
                chipset = ChipsetSpecs(
                    name = "Google Tensor G3",
                    cpu = "Nona-core (1x3.0 GHz + 4x2.45 GHz + 4x2.15 GHz)",
                    gpu = "Immortalis-G715s MC10",
                    processNode = "4nm",
                    scoreTier = "Flagship AI Architecture"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB"),
                    storageOptions = listOf("128GB", "256GB UFS 3.1")
                ),
                cameras = CameraSpecs(
                    rearSummary = "64 MP (wide, OIS) + 13 MP (ultrawide 120°)",
                    frontSummary = "13 MP (ultrawide)",
                    videoRecording = "4K@60fps, 1080p@240fps with OIS/EIS",
                    mainSensorMp = 64,
                    hasOis = true,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 4492,
                    wiredChargingWatts = 18,
                    wirelessChargingWatts = 7,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "Clean Pixel Experience",
                    supportYears = 7
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6E",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.2"
                ),
                build = BuildSpecs(
                    waterResistance = "IP67 dust/water resistant",
                    dimensionsMm = "152.1 x 72.7 x 8.9 mm",
                    weightGrams = 188,
                    materials = "Matte composite back, aluminum frame, Gorilla Glass 3"
                )
            ),
            pros = listOf(
                LocalizedString("Full 7 years of Android OS and security upgrades", "7 سنوات كاملة من التحديثات للنظام والأمان"),
                LocalizedString("Upgraded 120Hz Actua display with 2000 nits brightness", "شاشة أكتوا سلسة 120 هرتز بسطوع 2000 شمعة"),
                LocalizedString("Stellar point-and-shoot camera quality with Real Tone", "صور فوتوغرافية مذهلة بنقاوة بكسل وألوان طبيعية"),
                LocalizedString("Clean software free of unwanted bloatware", "واجهة بكسل الخام السلسة بدون أي تطبيقات مزعجة")
            ),
            cons = listOf(
                LocalizedString("Slow 18W wired charging speed", "شحن بطيء بقدرة 18 واط فقط"),
                LocalizedString("Screen bezels are noticeably chunky", "حواف الشاشة بارزة وسميكة نسبياً"),
                LocalizedString("Battery life is average under heavy 5G data", "عمر البطارية متوسط عند الاستخدام المكثف لشبكات 5G")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Best camera under $500 paired with an astonishing 7-year update guarantee. A high-value purchase for long-term users.",
                    ar = "أفضل كاميرا في فئة ما دون 500 دولار مع ضمان تحديثات مذهل لـ 7 سنوات. شراء ذكي جداً للاستخدام طويل الأمد."
                ),
                valueScore = 9.1f
            )
        ),

        Phone(
            id = "asus-rog-phone-9-pro",
            name = "Asus ROG Phone 9 Pro",
            brand = "Asus",
            releaseYear = 2024,
            approxPriceUsd = 1199,
            category = PhoneCategory.GAMING,
            isLatest = true,
            accentColorHex = "#DC2626",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.78\"",
                    type = "LTPO AMOLED, 185Hz Refresh Rate, AniMe Vision LED back",
                    refreshRateHz = 185,
                    resolution = "1080 x 2400 pixels (~388 ppi)",
                    peakBrightnessNits = 2500
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Elite",
                    cpu = "Octa-core 4.32 GHz Oryon",
                    gpu = "Adreno 830 (Overclocked profile)",
                    processNode = "3nm",
                    scoreTier = "Ultimate Gaming Engine"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("16GB", "24GB LPDDR5X"),
                    storageOptions = listOf("512GB", "1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Gimbal OIS (Sony Lytia) + 32 MP (3x telephoto, OIS) + 13 MP (ultrawide)",
                    frontSummary = "32 MP RGBW",
                    videoRecording = "8K@30fps, 4K@60fps HDR10+",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 5800,
                    wiredChargingWatts = 65,
                    wirelessChargingWatts = 15,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "ROG UI / ZenUI",
                    supportYears = 4
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "Dual USB Type-C (bottom & side mounted)"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 water & dust resistance",
                    dimensionsMm = "163.8 x 76.8 x 8.9 mm",
                    weightGrams = 227,
                    materials = "Aluminum frame, AniMe Vision programmable mini-LED back matrix"
                )
            ),
            pros = listOf(
                LocalizedString("World-first ultra-fast 185Hz esports refresh rate", "أسرع شاشة ألعاب في العالم بتردد مذهل 185 هرتز"),
                LocalizedString("AirTrigger ultrasonic shoulder buttons for console-like controls", "أزرار كتف لمسية AirTrigger لتحكم شبيه بأذرع الألعاب"),
                LocalizedString("Dual USB-C ports allowing side-charging while gaming", "منفذا USB-C للشحن الجانبي المريح أثناء الإمساك بالهاتف"),
                LocalizedString("Programmable AniMe Vision LED back panel displays retro games", "شاشة ليد خلفية تفاعلية لعرض الرسوم والألعاب الكلاسيكية")
            ),
            cons = listOf(
                LocalizedString("Gamer styling is not suited for everyone", "تصميم شبابي صاخب مخصص لمحبي الألعاب"),
                LocalizedString("Heavy at 227g and large footprint", "وزن ثقيل وحجم كبير في الجيب"),
                LocalizedString("Software update commitment is only 4 years", "دعم التحديثات 4 سنوات فقط مقارنة بسامسونج وغوغل")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.DEPENDS,
                reason = LocalizedString(
                    en = "Depends on use. The unchallenged king of competitive mobile gaming with 185Hz and shoulder triggers, but expensive for casual users.",
                    ar = "حسب الاستخدام. الملك المطلق لألعاب الموبايل التنافسية بتردد 185 هرتز وأزرار الكتف، لكنه مكلف للمستخدم العادي."
                ),
                valueScore = 8.6f
            )
        ),

        Phone(
            id = "huawei-pura-70-ultra",
            name = "Huawei Pura 70 Ultra",
            brand = "Huawei",
            releaseYear = 2024,
            approxPriceUsd = 1299,
            category = PhoneCategory.CAMERA,
            isLatest = false,
            accentColorHex = "#059669",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.8\"",
                    type = "LTPO OLED, 120Hz, HDR, 2500 nits",
                    refreshRateHz = 120,
                    resolution = "1260 x 2844 pixels (~460 ppi)",
                    peakBrightnessNits = 2500
                ),
                chipset = ChipsetSpecs(
                    name = "Kirin 9010",
                    cpu = "Octa-core Taishan architecture",
                    gpu = "Maleoon 910",
                    processNode = "7nm",
                    scoreTier = "Custom Huawei Silicon"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("16GB"),
                    storageOptions = listOf("512GB", "1TB NM card slot")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP retractable 1-inch (Sensor-shift OIS, f/1.6-4.0 variable) + 50 MP (3.5x macro telephoto) + 40 MP (ultrawide)",
                    frontSummary = "13 MP (ultrawide, autofocus)",
                    videoRecording = "4K@60fps, Ultra High-Speed Snapshot at 300 km/h",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 5200,
                    wiredChargingWatts = 100,
                    wirelessChargingWatts = 80,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "EMUI 14.2 / HarmonyOS 4.2",
                    uiSkin = "EMUI / HarmonyOS",
                    supportYears = 4
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6",
                    bluetoothVersion = "5.2",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.1 Gen 1"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 (2m for 30 min)",
                    dimensionsMm = "162.6 x 75.1 x 8.4 mm",
                    weightGrams = 226,
                    materials = "Star-pattern embossed eco-leather, Crystal Armor Kunlun glass"
                )
            ),
            pros = listOf(
                LocalizedString("Mesmerizing retractable mechanical 1-inch camera lens", "عدسة ميكانيكية متحركة ومستشعر 1 إنش عملاق بصوت حقيقي"),
                LocalizedString("Mind-boggling high-speed snapshot capturing motion at 300 km/h", "التقاط فائق السرعة يجمد الحركة حتى سرعة 300 كم/س"),
                LocalizedString("Exceptional telemacro camera for microscopic detail", "كاميرا ماكرو مقربة خرافية لتفاصيل فائقة الدقة"),
                LocalizedString("Kunlun Crystal Armor glass is extremely shatterproof", "زجاج كونلون المقوى مقاوم للصدمات والكسر بشكل مبهر")
            ),
            cons = listOf(
                LocalizedString("Lacks native Google Play Services (requires MicroG/GBox)", "لا يدعم خدمات جوجل بلاي الرسمية بشكل افتراضي"),
                LocalizedString("Kirin 9010 processor is less efficient than Snapdragon 8 Gen 3", "المعالج أضعف في كفاءة الطاقة مقارنة بسنابدراجون"),
                LocalizedString("Very premium price tag", "سعر مرتفع جداً يناطح أغلى هواتف آبل وسامسونج")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.DEPENDS,
                reason = LocalizedString(
                    en = "Depends on use. One of the greatest camera phones ever made mechanically, but the lack of native Google services limits mass recommendation.",
                    ar = "حسب الاستخدام. تحفة ميكانيكية وإحدى أعظم كاميرات الهواتف في التاريخ، لكن غياب خدمات جوجل رسمياً يجعله غير مناسب للجميع."
                ),
                valueScore = 8.1f
            )
        ),

        Phone(
            id = "honor-magic-7-pro",
            name = "Honor Magic 7 Pro",
            brand = "Honor",
            releaseYear = 2024,
            approxPriceUsd = 1050,
            category = PhoneCategory.CAMERA,
            isLatest = true,
            accentColorHex = "#0284C7",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.8\"",
                    type = "LTPO OLED, 120Hz, 4320Hz PWM risk-free dimming, 5000 nits",
                    refreshRateHz = 120,
                    resolution = "1280 x 2800 pixels (~453 ppi)",
                    peakBrightnessNits = 5000
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Elite",
                    cpu = "Octa-core 4.32 GHz Oryon",
                    gpu = "Adreno 830",
                    processNode = "3nm",
                    scoreTier = "Flagship Tier 1"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB", "1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP OmniVision OV50H (OIS, variable aperture f/1.4-2.0) + 200 MP (3x periscope telephoto, OIS) + 50 MP (ultrawide)",
                    frontSummary = "50 MP + 3D ToF Face Unlock biometric sensor",
                    videoRecording = "4K@60fps 10-bit Log, Harcourt Portrait mode",
                    mainSensorMp = 200,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 5850,
                    wiredChargingWatts = 100,
                    wirelessChargingWatts = 80,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "MagicOS 9.0 (with Google Play Services)",
                    supportYears = 5
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.2 Gen 1"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 & IP69 dust/water resistant",
                    dimensionsMm = "162.7 x 77.1 x 8.8 mm",
                    weightGrams = 223,
                    materials = "NanoCrystal Shield drop-resistant glass, aircraft metal frame"
                )
            ),
            pros = listOf(
                LocalizedString("200MP periscope zoom combined with 50MP variable aperture", "كاميرا زوم بيريسكوب 200 ميجابكسل مع فتحة عدسة متغيرة"),
                LocalizedString("3D ToF Face Unlock as secure as Apple Face ID", "بصمة وجه ثلاثية الأبعاد آمنة للمدفوعات مثل آيفون"),
                LocalizedString("4320Hz ultra-high PWM eye comfort screen protection", "حماية العين بحزمة تردد 4320Hz بدون وميض مجهد"),
                LocalizedString("Full Google Play Services out-of-the-box", "خدمات جوجل بلاي كاملة ورسمية مدمجة")
            ),
            cons = listOf(
                LocalizedString("Dual pill-shaped front notch takes up screen status area", "فتحة الكاميرا الأمامية المزدوجة تأخذ مساحة من أعلى الشاشة"),
                LocalizedString("Heavy device at 223g", "وزن ثقيل نسبياً في اليد")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "A full-featured flagship with Snapdragon 8 Elite, 200MP zoom, 3D Face Unlock, and monster battery. Highly recommended.",
                    ar = "هاتف رائد متكامل بمعالج 8 إيليت، وزوم 200 ميجابكسل، وبصمة وجه ثلاثية الأبعاد وبطارية عملاقة. نوصي به بشدة."
                ),
                valueScore = 9.3f
            )
        ),

        Phone(
            id = "oppo-find-x8-pro",
            name = "Oppo Find X8 Pro",
            brand = "Oppo",
            releaseYear = 2024,
            approxPriceUsd = 999,
            category = PhoneCategory.CAMERA,
            isLatest = true,
            accentColorHex = "#10B981",
            rating = 4.8f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.78\"",
                    type = "LTPO AMOLED, 1B colors, 120Hz, Dolby Vision, 4500 nits",
                    refreshRateHz = 120,
                    resolution = "1264 x 2780 pixels (~450 ppi)",
                    peakBrightnessNits = 4500
                ),
                chipset = ChipsetSpecs(
                    name = "MediaTek Dimensity 9400",
                    cpu = "Octa-core (1x3.63 GHz Cortex-X925 + 3x3.3 GHz + 4x2.4 GHz)",
                    gpu = "Immortalis-G925",
                    processNode = "3nm",
                    scoreTier = "Top Tier Performance Leader"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB, 1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Sony LYT-808 (OIS) + 50 MP (3x telephoto) + 50 MP (6x periscope) + 50 MP (ultrawide) Hasselblad quad-cam",
                    frontSummary = "32 MP (wide)",
                    videoRecording = "4K@60fps 10-bit Dolby Vision HDR",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 5910,
                    wiredChargingWatts = 80,
                    wirelessChargingWatts = 50,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 15",
                    uiSkin = "ColorOS 15",
                    supportYears = 5
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.1"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 & IP69 water/dust resistant",
                    dimensionsMm = "162.3 x 76.7 x 8.2 mm",
                    weightGrams = 215,
                    materials = "Aluminum frame, micro-quad curved glass back, Quick Button"
                )
            ),
            pros = listOf(
                LocalizedString("Dual periscope optical zoom system (3x portrait + 6x long range)", "نظام زوم مزدوج حقيقي (3x للبورتريه و6x للتقريب البعيد)"),
                LocalizedString("Dimensity 9400 3nm chip provides phenomenal efficiency and cooler temps", "معالج دايمنستي 9400 يقدم كفاءة حرارية وبطارية أسطورية"),
                LocalizedString("Quick Button for instantaneous camera snapshots", "زر Quick Button السريع المخصص للكاميرا"),
                LocalizedString("5910mAh glacier battery with 80W flash charging", "بطارية جلاسير 5910 مللي أمبير مع شحن سريع 80 واط")
            ),
            cons = listOf(
                LocalizedString("Slightly slippery without a protective case", "سطح خلفي أملس يحتاج إلى غطاء حماية"),
                LocalizedString("ColorOS has some preloaded promotional services", "واجهة كلر أو إس تحتوي على بعض خدمات الترويج")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Spectacular quad 50MP Hasselblad camera array with dual periscopes and all-day battery life. One of 2024-2025's best phones.",
                    ar = "أربع كاميرات هاسيلبلاد 50 ميجابكسل مع عدستي تقريب وبطارية لا تنفد. من أروع هواتف العام."
                ),
                valueScore = 9.4f
            )
        ),

        Phone(
            id = "motorola-edge-50-ultra",
            name = "Motorola Edge 50 Ultra",
            brand = "Motorola",
            releaseYear = 2024,
            approxPriceUsd = 899,
            category = PhoneCategory.FLAGSHIP,
            isLatest = false,
            accentColorHex = "#D97706",
            rating = 4.6f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.7\"",
                    type = "pOLED, 144Hz, HDR10+, 10-bit, 2500 nits, Pantone validated",
                    refreshRateHz = 144,
                    resolution = "1220 x 2712 pixels (~446 ppi)",
                    peakBrightnessNits = 2500
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8s Gen 3",
                    cpu = "Octa-core 3.0 GHz",
                    gpu = "Adreno 735",
                    processNode = "4nm",
                    scoreTier = "Sub-Flagship Performance"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("512GB", "1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, OIS) + 64 MP (3x periscope telephoto, OIS) + 50 MP (ultrawide, macro)",
                    frontSummary = "50 MP (autofocus)",
                    videoRecording = "4K@60fps with adaptive stabilization",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 4500,
                    wiredChargingWatts = 125,
                    wirelessChargingWatts = 50,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 14 (Hello UI)",
                    uiSkin = "Hello UI (Clean Moto experience)",
                    supportYears = 3
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = true,
                    usbType = "USB Type-C 3.1 Gen 2, DisplayPort 1.4"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 dust/water resistant",
                    dimensionsMm = "161.1 x 72.4 x 8.6 mm",
                    weightGrams = 197,
                    materials = "Real Nordic Wood or Vegan Leather back, sandblasted aluminum frame"
                )
            ),
            pros = listOf(
                LocalizedString("Unique natural real wood back or soft vegan leather finishes", "ظهر مميز جداً من الخشب الإسكندنافي الحقيقي أو الجلد النباتي"),
                LocalizedString("Super-fast 125W TurboPower charging (full charge in ~20 min)", "شاحن تيربو بقوة 125 واط يشحن الهاتف كاملاً في 20 دقيقة"),
                LocalizedString("Smooth 144Hz Pantone color-calibrated display", "شاشة 144 هرتز سلسة بمعايرة ألوان بانتون الدقيقة"),
                LocalizedString("Clean, near-stock Android with handy Moto gestures", "نظام أندرويد شبه خام مع حركات موتو الشهيرة المفيدة")
            ),
            cons = listOf(
                LocalizedString("Battery capacity is modest at 4500mAh", "سعة البطارية 4500 مللي أمبير أقل من المنافسين"),
                LocalizedString("Snapdragon 8s Gen 3 is one step down from full 8 Gen 3", "معالج 8s أقل قوة بدرجة من المعالج الرئيسي الكامل"),
                LocalizedString("Software update commitment is only 3 major versions", "دعم التحديثات يقتصر على 3 سنوات فقط")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Sensational design aesthetics, wood textures, 125W charging, and 144Hz screen. Recommended for those wanting style and speed.",
                    ar = "تصميم أنيق بلمسات الخشب الفاخر، وشاحن 125 واط وشاشة 144 هرتز. خيار رائع لعشاق التميز والسرعة."
                ),
                valueScore = 8.7f
            )
        ),

        Phone(
            id = "xiaomi-poco-f6-pro",
            name = "Xiaomi Poco F6 Pro",
            brand = "Xiaomi",
            releaseYear = 2024,
            approxPriceUsd = 480,
            category = PhoneCategory.GAMING,
            isLatest = false,
            accentColorHex = "#CA8A04",
            rating = 4.7f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.67\"",
                    type = "WQHD+ Flow AMOLED, 120Hz, Dolby Vision, 4000 nits",
                    refreshRateHz = 120,
                    resolution = "1440 x 3200 pixels (~526 ppi)",
                    peakBrightnessNits = 4000
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 8 Gen 2",
                    cpu = "Octa-core 3.2 GHz",
                    gpu = "Adreno 740",
                    processNode = "4nm",
                    scoreTier = "Flagship Proven Beast"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB", "16GB"),
                    storageOptions = listOf("256GB", "512GB, 1TB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Light Fusion 800 (OIS) + 8 MP (ultrawide) + 2 MP (macro)",
                    frontSummary = "16 MP (wide)",
                    videoRecording = "8K@24fps, 4K@60fps",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 120,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (HyperOS)",
                    uiSkin = "HyperOS for POCO",
                    supportYears = 3
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 7",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP54 splash/dust resistant",
                    dimensionsMm = "160.9 x 75.0 x 8.2 mm",
                    weightGrams = 209,
                    materials = "Matte velvet glass back, aluminum alloy frame"
                )
            ),
            pros = listOf(
                LocalizedString("Stunning 2K 1440p flagship-resolution display under $500", "شاشة فائقة الدقة 2K بمعدل 1440 بكسل بسعر أقل من 500 دولار"),
                LocalizedString("Snapdragon 8 Gen 2 delivers tier-1 gaming speed", "معالج سنابدراجون 8 الجيل الثاني يقدم أداء ألعاب خارق"),
                LocalizedString("Blazing 120W HyperCharge powers 100% in 19 minutes", "شاحن 120 واط فائق السرعة يملأ البطارية في 19 دقيقة"),
                LocalizedString("Metal frame gives a reassuringly solid flagship feel", "إطار معدني متين يمنح شعوراً بالفخامة")
            ),
            cons = listOf(
                LocalizedString("Only IP54 splash resistance, no full water submersion", "مقاومة رذاذ الماء IP54 فقط دون مقاومة غمر كامل"),
                LocalizedString("Secondary cameras (8MP ultrawide and 2MP macro) are basic", "الكاميرات الثانوية عادية ومتواضعة مقارنة بالأساسية"),
                LocalizedString("No wireless charging", "لا يدعم الشحن اللاسلكي")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Unbeatable horsepower per dollar. If you want a 2K screen and flagship gaming speed under $500, this is the default pick.",
                    ar = "أقوى عتاد ممكن مقابل كل دولار. إذا أردت شاشة 2K وأداء ألعاب رائد بأقل من 500 دولار، فهذا خيارك الأول."
                ),
                valueScore = 9.5f
            )
        ),

        Phone(
            id = "samsung-galaxy-s24-fe",
            name = "Samsung Galaxy S24 FE",
            brand = "Samsung",
            releaseYear = 2024,
            approxPriceUsd = 649,
            category = PhoneCategory.MID_RANGE,
            isLatest = true,
            accentColorHex = "#3B82F6",
            rating = 4.6f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.7\"",
                    type = "Dynamic AMOLED 2X, 120Hz, HDR10+, 1900 nits",
                    refreshRateHz = 120,
                    resolution = "1080 x 2340 pixels (~385 ppi)",
                    peakBrightnessNits = 1900
                ),
                chipset = ChipsetSpecs(
                    name = "Exynos 2400e (4nm)",
                    cpu = "Deca-core (10-core AMD Xclipse 940 GPU)",
                    gpu = "Xclipse 940 (Hardware Ray Tracing)",
                    processNode = "4nm",
                    scoreTier = "Flagship Tier 1.5"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB"),
                    storageOptions = listOf("128GB", "256GB", "512GB")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, OIS) + 8 MP (3x telephoto, OIS) + 12 MP (ultrawide)",
                    frontSummary = "10 MP (wide)",
                    videoRecording = "8K@30fps, 4K@60fps",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 4700,
                    wiredChargingWatts = 25,
                    wirelessChargingWatts = 15,
                    hasReverseCharging = true
                ),
                os = OsSpecs(
                    osName = "Android 14 (One UI 6.1 with Galaxy AI)",
                    uiSkin = "One UI (Full Galaxy AI suite)",
                    supportYears = 7
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6E",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 3.2, DisplayPort"
                ),
                build = BuildSpecs(
                    waterResistance = "IP68 water & dust resistant",
                    dimensionsMm = "162.0 x 77.3 x 8.0 mm",
                    weightGrams = 213,
                    materials = "Armor Aluminum frame, Gorilla Glass Victus+ front & back"
                )
            ),
            pros = listOf(
                LocalizedString("Full Galaxy AI suite included with Circle to Search and Live Translate", "حزمة مزايا الذكاء الاصطناعي Galaxy AI كاملة"),
                LocalizedString("7 years of guaranteed OS updates matching the S24 Ultra", "7 سنوات كاملة من التحديثات مثل الفلاجشيب الأغلى"),
                LocalizedString("Full IP68 water resistance and wireless charging", "مقاومة ماء كاملة IP68 مع دعم الشحن اللاسلكي"),
                LocalizedString("Larger 6.7-inch display upgrade over previous generation", "شاشة كبيرة 6.7 إنش ترقية ملحوظة عن الجيل السابق")
            ),
            cons = listOf(
                LocalizedString("Slow 25W charging takes over 75 minutes", "شحن بطيء بقدرة 25 واط يستغرق أكثر من ساعة وربع"),
                LocalizedString("Telephoto camera is only 8MP", "كاميرا الزوم بدقة 8 ميجابكسل فقط"),
                LocalizedString("Base model starts at 128GB storage", "سعة البدء 128 جيجابايت فقط")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Gets you 90% of the flagship S24+ experience for $350 less, along with 7 years of software support and full Galaxy AI.",
                    ar = "يقدم 90% من تجربة S24 بلس بسعر أقل بـ 350 دولاراً مع 7 سنوات تحديثات وذكاء سامسونج الاصطناعي كاملاً."
                ),
                valueScore = 9.0f
            )
        ),

        Phone(
            id = "oneplus-nord-4",
            name = "OnePlus Nord 4",
            brand = "OnePlus",
            releaseYear = 2024,
            approxPriceUsd = 399,
            category = PhoneCategory.MID_RANGE,
            isLatest = false,
            accentColorHex = "#0EA5E9",
            rating = 4.7f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.74\"",
                    type = "Fluid AMOLED, 120Hz, HDR10+, Ultra HDR, 2150 nits",
                    refreshRateHz = 120,
                    resolution = "1240 x 2772 pixels (~450 ppi)",
                    peakBrightnessNits = 2150
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 7+ Gen 3",
                    cpu = "Octa-core 2.8 GHz (Cortex-X4 core)",
                    gpu = "Adreno 732",
                    processNode = "4nm",
                    scoreTier = "Flagship-Adjacent Performance"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB", "12GB", "16GB"),
                    storageOptions = listOf("128GB", "256GB, 512GB UFS 4.0")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Sony LYT-600 (OIS) + 8 MP (ultrawide 112°)",
                    frontSummary = "16 MP (wide)",
                    videoRecording = "4K@60fps, 1080p@120fps with EIS/OIS",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5500,
                    wiredChargingWatts = 100,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (OxygenOS 14.1)",
                    uiSkin = "OxygenOS",
                    supportYears = 4
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6",
                    bluetoothVersion = "5.4",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP65 water and dust resistance",
                    dimensionsMm = "162.6 x 75.0 x 8.0 mm",
                    weightGrams = 199,
                    materials = "All-metal unibody aluminum design, Aqua Touch display"
                )
            ),
            pros = listOf(
                LocalizedString("Rare all-metal aluminum unibody build in 2024", "هيكل معدني موحد من الألمنيوم فريد ونادر في فئته"),
                LocalizedString("Snapdragon 7+ Gen 3 punches far above its price weight", "معالج سنابدراجون 7+ يقدم قوة تقارع الفئات الرائدة"),
                LocalizedString("5500mAh battery with 100W SUPERVOOC charging", "بطارية 5500 مللي أمبير مع شاحن 100 واط فائق السرعة"),
                LocalizedString("Aqua Touch allows smooth screen usage with wet fingers", "تقنية اللمس في الشاشة تعمل حتى مع الأصابع المبللة")
            ),
            cons = listOf(
                LocalizedString("No dedicated telephoto optical camera", "لا توجد عدسة تقريب مخصصة"),
                LocalizedString("No wireless charging", "لا يدعم الشحن اللاسلكي")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Sensational mid-ranger! The premium all-metal body, top-tier 7+ Gen 3 chip, and 100W charging make it a top recommendation.",
                    ar = "هاتف متوسط مذهل! هيكل ألمنيوم فخم، ومعالج خارق وشحن 100 واط يجعله في مقدمة التوصيات."
                ),
                valueScore = 9.5f
            )
        ),

        Phone(
            id = "realme-13-pro-plus",
            name = "Realme 13 Pro+",
            brand = "Realme",
            releaseYear = 2024,
            approxPriceUsd = 320,
            category = PhoneCategory.CAMERA,
            isLatest = false,
            accentColorHex = "#A855F7",
            rating = 4.6f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.7\"",
                    type = "Curved AMOLED, 120Hz, 2000 nits, Pro-XDR",
                    refreshRateHz = 120,
                    resolution = "1080 x 2412 pixels (~394 ppi)",
                    peakBrightnessNits = 2000
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 7s Gen 2",
                    cpu = "Octa-core 2.4 GHz",
                    gpu = "Adreno 710",
                    processNode = "4nm",
                    scoreTier = "Balanced Efficiency"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("8GB", "12GB"),
                    storageOptions = listOf("256GB", "512GB UFS 3.1")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP Sony LYT-701 (OIS) + 50 MP Sony LYT-600 (3x periscope telephoto, OIS) + 8 MP (ultrawide)",
                    frontSummary = "32 MP (wide)",
                    videoRecording = "4K@30fps, 1080p@60fps with OIS/EIS",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = true
                ),
                battery = BatterySpecs(
                    capacityMah = 5200,
                    wiredChargingWatts = 80,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (Realme UI 5.0)",
                    uiSkin = "Realme UI",
                    supportYears = 3
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 6",
                    bluetoothVersion = "5.2",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0"
                ),
                build = BuildSpecs(
                    waterResistance = "IP65 dust/water resistance",
                    dimensionsMm = "161.3 x 73.9 x 8.2 mm",
                    weightGrams = 185,
                    materials = "Monet-inspired glass or vegan leather, curved edges"
                )
            ),
            pros = listOf(
                LocalizedString("Sony LYT-600 3x periscope zoom for under $350", "كاميرا زوم بيريسكوب حقيقية بمستشعر سوني بسعر أقل من 350 دولار"),
                LocalizedString("Claude Monet-inspired art painting back aesthetics", "تصميم فني مستوحى من لوحات الفنان كلود مونيه"),
                LocalizedString("80W fast charging powers up to 50% in 19 minutes", "شحن سريع 80 واط يصل لـ 50% خلال 19 دقيقة")
            ),
            cons = listOf(
                LocalizedString("Snapdragon 7s Gen 2 is the same chip from last year's predecessor", "نفس المعالج من الجيل السابق دون ترقية في الأداء"),
                LocalizedString("Mono sound speaker tuning could be punchier", "مستوى الصوت في السماعات كان يمكن أن يكون أعمق")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "The premier budget portrait camera phone. The dual Sony LYT sensor setup with real 3x periscope zoom is unmatched at this price.",
                    ar = "أفضل هاتف بورتريه وكاميرا بميزانية منخفضة. وجود مستشعرات سوني مع زوم 3x حقيقي لا يضاهى بهذا السعر."
                ),
                valueScore = 9.2f
            )
        ),

        Phone(
            id = "motorola-moto-g84",
            name = "Motorola Moto G84 5G",
            brand = "Motorola",
            releaseYear = 2024,
            approxPriceUsd = 199,
            category = PhoneCategory.BUDGET,
            isLatest = false,
            accentColorHex = "#E11D48",
            rating = 4.5f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.55\"",
                    type = "pOLED, 120Hz, 1B colors, 1300 nits",
                    refreshRateHz = 120,
                    resolution = "1080 x 2400 pixels (~402 ppi)",
                    peakBrightnessNits = 1300
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 695 5G",
                    cpu = "Octa-core 2.2 GHz",
                    gpu = "Adreno 619",
                    processNode = "6nm",
                    scoreTier = "Budget Everyday Hero"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("12GB"),
                    storageOptions = listOf("256GB (microSD slot)")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, OIS) + 8 MP (ultrawide, macro, autofocus)",
                    frontSummary = "16 MP (wide)",
                    videoRecording = "1080p@60fps",
                    mainSensorMp = 50,
                    hasOis = true,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 30,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (My UX)",
                    uiSkin = "Moto Clean Experience",
                    supportYears = 2
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 5",
                    bluetoothVersion = "5.1",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0, 3.5mm headphone jack"
                ),
                build = BuildSpecs(
                    waterResistance = "IP54 water-repellent design",
                    dimensionsMm = "160.0 x 74.4 x 7.6 mm",
                    weightGrams = 166,
                    materials = "Pantone Viva Magenta vegan leather, ultralight frame"
                )
            ),
            pros = listOf(
                LocalizedString("Glorious 120Hz pOLED screen with 12GB RAM standard for $199", "شاشة pOLED بتردد 120 هرتز ورام 12 جيجابايت بسعر 199 دولار"),
                LocalizedString("Main camera includes Optical Image Stabilization (OIS)", "الكاميرا الأساسية تدعم التثبيت البصري OIS الحقيقي"),
                LocalizedString("Includes 3.5mm headphone jack and microSD slot", "يحتوي على منفذ سماعات 3.5 ملم ومنفذ كارت ميموري"),
                LocalizedString("Ultra-lightweight 166g with vegan leather texture", "خفيف الوزن جداً 166 غرام مع لمسة جلد نباتي فاخرة")
            ),
            cons = listOf(
                LocalizedString("Snapdragon 695 cannot record 4K video", "المعالج لا يدعم تصوير فيديو 4K"),
                LocalizedString("Only 1 major OS upgrade commitment", "تحديث نظام رئيسي واحد فقط")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "Astonishing value under $200. 12GB RAM, 256GB storage, 120Hz OLED, and OIS camera are unbeatable at this budget.",
                    ar = "قيمة لا تصدق بأقل من 200 دولار. رام 12 جيجا وسعة 256 جيجا وشاشة أوليد 120 هرتز مع مثبت بصري!"
                ),
                valueScore = 9.4f
            )
        ),

        Phone(
            id = "samsung-galaxy-a15-5g",
            name = "Samsung Galaxy A15 5G",
            brand = "Samsung",
            releaseYear = 2024,
            approxPriceUsd = 179,
            category = PhoneCategory.BUDGET,
            isLatest = false,
            accentColorHex = "#2563EB",
            rating = 4.4f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.5\"",
                    type = "Super AMOLED, 90Hz, 800 nits (HBM)",
                    refreshRateHz = 90,
                    resolution = "1080 x 2340 pixels (~396 ppi)",
                    peakBrightnessNits = 800
                ),
                chipset = ChipsetSpecs(
                    name = "MediaTek Dimensity 6100+",
                    cpu = "Octa-core 2.2 GHz",
                    gpu = "Mali-G57 MC2",
                    processNode = "6nm",
                    scoreTier = "Budget 5G"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("4GB", "6GB", "8GB"),
                    storageOptions = listOf("128GB", "256GB (microSD slot)")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, AF) + 5 MP (ultrawide) + 2 MP (macro)",
                    frontSummary = "13 MP (wide)",
                    videoRecording = "1080p@30fps",
                    mainSensorMp = 50,
                    hasOis = false,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 25,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (One UI 6)",
                    uiSkin = "One UI",
                    supportYears = 4
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 5",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0, 3.5mm headphone jack"
                ),
                build = BuildSpecs(
                    waterResistance = "No official IP rating",
                    dimensionsMm = "160.1 x 76.8 x 8.4 mm",
                    weightGrams = 200,
                    materials = "Key Island plastic frame, glossy plastic back"
                )
            ),
            pros = listOf(
                LocalizedString("Vibrant 90Hz Super AMOLED screen at entry level", "شاشة سوبر أموليد 90 هرتز رائعة في فئة اقتصادية"),
                LocalizedString("Guaranteed 4 OS updates and 5 years security patches", "4 سنوات تحديثات نظام و5 سنوات أمان"),
                LocalizedString("Long battery longevity with 5000mAh", "بطارية ممتازة تدوم طويلاً بسعة 5000 مللي أمبير"),
                LocalizedString("Includes 3.5mm audio jack and microSD support", "منفذ سماعات ومنفذ بطاقة ذاكرة")
            ),
            cons = listOf(
                LocalizedString("Waterdrop notch looks dated in 2024", "فتحة الكاميرا الأمامية بشكل النوتش القديم"),
                LocalizedString("No water or dust resistance rating", "لا توجد مقاومة معتمدة للماء أو الغبار"),
                LocalizedString("Camera lacks optical stabilization (OIS)", "الكاميرا تفتقر للتثبيت البصري")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.YES,
                reason = LocalizedString(
                    en = "The safest sub-$180 recommendation thanks to 4 years of OS updates and a rich Super AMOLED screen.",
                    ar = "الخيار الأضمن بأقل من 180 دولاراً بفضل 4 سنوات من تحديثات النظام وشاشة سوبر أموليد الرائعة."
                ),
                valueScore = 9.0f
            )
        ),

        Phone(
            id = "xiaomi-redmi-13c",
            name = "Xiaomi Redmi 13C",
            brand = "Xiaomi",
            releaseYear = 2024,
            approxPriceUsd = 129,
            category = PhoneCategory.BUDGET,
            isLatest = false,
            accentColorHex = "#16A34A",
            rating = 4.2f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.74\"",
                    type = "IPS LCD, 90Hz, 600 nits (HBM)",
                    refreshRateHz = 90,
                    resolution = "720 x 1600 pixels (~260 ppi)",
                    peakBrightnessNits = 600
                ),
                chipset = ChipsetSpecs(
                    name = "MediaTek Helio G85",
                    cpu = "Octa-core 2.0 GHz",
                    gpu = "Mali-G52 MC2",
                    processNode = "12nm",
                    scoreTier = "Entry Level"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("4GB", "6GB", "8GB"),
                    storageOptions = listOf("128GB", "256GB eMMC 5.1")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, PDAF) + 2 MP (macro) + 0.08 MP (auxiliary)",
                    frontSummary = "8 MP (wide)",
                    videoRecording = "1080p@30fps",
                    mainSensorMp = 50,
                    hasOis = false,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 18,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 13 (MIUI / HyperOS)",
                    uiSkin = "HyperOS",
                    supportYears = 2
                ),
                connectivity = ConnectivitySpecs(
                    has5g = false,
                    wifiStandard = "Wi-Fi 5",
                    bluetoothVersion = "5.3",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0, 3.5mm headphone jack"
                ),
                build = BuildSpecs(
                    waterResistance = "No official IP rating",
                    dimensionsMm = "168.0 x 78.0 x 8.09 mm",
                    weightGrams = 192,
                    materials = "Flat plastic frame, starry shimmer composite back"
                )
            ),
            pros = listOf(
                LocalizedString("Extremely affordable entry point with 90Hz screen", "سعر اقتصادي جداً ومناسب للجميع مع شاشة 90 هرتز"),
                LocalizedString("5000mAh battery easily lasts 2 days of basic use", "بطارية 5000 مللي أمبير تكفي يومين من الاستخدام الأساسي"),
                LocalizedString("Sleek flat-edge design and side fingerprint sensor", "تصميم عصري بحواف مسطحة وبصمة سريعة بالجانب")
            ),
            cons = listOf(
                LocalizedString("Screen resolution is only 720p HD+", "دقة الشاشة HD+ فقط وليست كاملة"),
                LocalizedString("No 5G support (4G LTE only)", "لا يدعم شبكات الجيل الخامس 5G"),
                LocalizedString("Older eMMC 5.1 storage is slow for heavy app installs", "ذاكرة التخزين eMMC بطيئة نسبياً عند تثبيت التطبيقات")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.DEPENDS,
                reason = LocalizedString(
                    en = "Acceptable only for tight budgets or secondary phones. If you can stretch $50 more, 1080p OLED models offer a massive upgrade.",
                    ar = "مقبول فقط للميزانيات الصارمة أو كهاتف ثانوي. إن استطعت زيادة 50 دولاراً فستحصل على شاشة 1080p أفضل بكثير."
                ),
                valueScore = 7.1f
            )
        ),

        Phone(
            id = "nokia-g42-5g",
            name = "Nokia G42 5G",
            brand = "Nokia",
            releaseYear = 2024,
            approxPriceUsd = 189,
            category = PhoneCategory.BUDGET,
            isLatest = false,
            accentColorHex = "#64748B",
            rating = 4.3f,
            specs = PhoneSpecs(
                display = DisplaySpecs(
                    size = "6.56\"",
                    type = "IPS LCD, 90Hz, 560 nits",
                    refreshRateHz = 90,
                    resolution = "720 x 1612 pixels (~269 ppi)",
                    peakBrightnessNits = 560
                ),
                chipset = ChipsetSpecs(
                    name = "Snapdragon 480+ 5G",
                    cpu = "Octa-core 2.2 GHz",
                    gpu = "Adreno 619",
                    processNode = "8nm",
                    scoreTier = "Entry 5G"
                ),
                memory = MemorySpecs(
                    ramOptions = listOf("6GB", "8GB"),
                    storageOptions = listOf("128GB", "256GB (microSD slot)")
                ),
                cameras = CameraSpecs(
                    rearSummary = "50 MP (wide, AF) + 2 MP (macro) + 2 MP (depth)",
                    frontSummary = "8 MP",
                    videoRecording = "1080p@30fps with OZO Audio capture",
                    mainSensorMp = 50,
                    hasOis = false,
                    hasTelephoto = false
                ),
                battery = BatterySpecs(
                    capacityMah = 5000,
                    wiredChargingWatts = 20,
                    wirelessChargingWatts = 0,
                    hasReverseCharging = false
                ),
                os = OsSpecs(
                    osName = "Android 14 (Stock Android)",
                    uiSkin = "Pure Android",
                    supportYears = 3
                ),
                connectivity = ConnectivitySpecs(
                    has5g = true,
                    wifiStandard = "Wi-Fi 5",
                    bluetoothVersion = "5.1",
                    hasNfc = true,
                    hasUwb = false,
                    usbType = "USB Type-C 2.0, 3.5mm headphone jack"
                ),
                build = BuildSpecs(
                    waterResistance = "IP52 splash resistance",
                    dimensionsMm = "165.0 x 75.8 x 8.55 mm",
                    weightGrams = 193,
                    materials = "65% recycled plastic, QuickFix user-repairable design"
                )
            ),
            pros = listOf(
                LocalizedString("QuickFix iFixit self-repairable screen, battery, and port", "قابلية الصيانة الذاتية بسهولة في المنزل عبر شراكة iFixit"),
                LocalizedString("Pure bloatware-free stock Android experience", "نظام أندرويد خام نقي بدون أي تطبيقات مكررة"),
                LocalizedString("Sub-$200 5G connectivity with good battery life", "اتصال 5G بأقل من 200 دولار وبطارية تدوم طويلاً")
            ),
            cons = listOf(
                LocalizedString("720p resolution LCD screen is subpar for text clarity", "شاشة LCD بدقة 720p متواضعة في نقاوة النصوص"),
                LocalizedString("Charging is capped at 20W", "سرعة الشحن 20 واط فقط"),
                LocalizedString("Camera performance is weak in low light", "أداء الكاميرا متواضع في الإضاءة المنخفضة")
            ),
            verdict = RecommendationVerdict(
                status = RecommendationStatus.DEPENDS,
                reason = LocalizedString(
                    en = "Recommended specifically for fans of repairability and stock Android. Otherwise, competing models offer sharper 1080p screens.",
                    ar = "نوصي به خصيصاً لمن تهمه سهولة صيانة الهاتف بنفسه ونظام أندرويد الخام، وإلا فإن المنافسين يقدمون شاشات أفضل بدقة 1080p."
                ),
                valueScore = 7.6f
            )
        )
    )

    fun getBrands(): List<String> = listOf(
        "All",
        "Samsung",
        "Apple",
        "Xiaomi",
        "Google",
        "OnePlus",
        "Huawei",
        "Honor",
        "Oppo",
        "Realme",
        "Motorola",
        "Nokia",
        "Asus"
    )
}
