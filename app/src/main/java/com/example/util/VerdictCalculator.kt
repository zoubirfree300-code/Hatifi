package com.example.util

import com.example.model.*
import kotlin.math.max
import kotlin.math.min

/**
 * Intelligent algorithmic calculator that scores phone specifications
 * against retail price to determine recommendation verdict and rationale.
 */
object VerdictCalculator {

    fun calculateVerdict(
        priceUsd: Int,
        category: PhoneCategory,
        specs: PhoneSpecs
    ): RecommendationVerdict {
        // 1. Display Score (Max 25 pts)
        var displayScore = 0
        if (specs.display.refreshRateHz >= 144) displayScore += 12
        else if (specs.display.refreshRateHz >= 120) displayScore += 10
        else if (specs.display.refreshRateHz >= 90) displayScore += 6
        else displayScore += 3

        if (specs.display.type.contains("OLED", ignoreCase = true) || 
            specs.display.type.contains("AMOLED", ignoreCase = true)) {
            displayScore += 8
            if (specs.display.type.contains("LTPO", ignoreCase = true)) displayScore += 3
        } else {
            displayScore += 4
        }
        if (specs.display.peakBrightnessNits >= 2000) displayScore += 2

        // 2. Performance Score (Max 30 pts)
        var perfScore = 0
        val chipName = specs.chipset.name.lowercase()
        when {
            chipName.contains("elite") || chipName.contains("gen 3") || 
            chipName.contains("a18") || chipName.contains("9400") || chipName.contains("9300") -> {
                perfScore += 26
            }
            chipName.contains("gen 2") || chipName.contains("a17") || 
            chipName.contains("tensor g4") || chipName.contains("8s gen 3") -> {
                perfScore += 22
            }
            chipName.contains("7+ gen") || chipName.contains("8300") || chipName.contains("7 gen") -> {
                perfScore += 18
            }
            chipName.contains("1480") || chipName.contains("7020") || chipName.contains("7050") -> {
                perfScore += 14
            }
            else -> perfScore += 10
        }
        val maxRam = specs.memory.ramOptions.mapNotNull { it.filter { ch -> ch.isDigit() }.toIntOrNull() }.maxOrNull() ?: 8
        if (maxRam >= 16) perfScore += 4
        else if (maxRam >= 12) perfScore += 3
        else if (maxRam >= 8) perfScore += 2

        // 3. Camera Score (Max 25 pts)
        var camScore = 0
        if (specs.cameras.hasOis) camScore += 8
        if (specs.cameras.hasTelephoto) camScore += 7
        if (specs.cameras.mainSensorMp >= 108) camScore += 5
        else if (specs.cameras.mainSensorMp >= 50) camScore += 4
        else camScore += 2
        if (specs.cameras.videoRecording.contains("8K") || specs.cameras.videoRecording.contains("4K@60")) camScore += 5
        else if (specs.cameras.videoRecording.contains("4K")) camScore += 3

        // 4. Battery & Charging Score (Max 20 pts)
        var batteryScore = 0
        if (specs.battery.capacityMah >= 5500) batteryScore += 9
        else if (specs.battery.capacityMah >= 5000) batteryScore += 8
        else if (specs.battery.capacityMah >= 4500) batteryScore += 6
        else batteryScore += 4

        if (specs.battery.wiredChargingWatts >= 80) batteryScore += 7
        else if (specs.battery.wiredChargingWatts >= 45) batteryScore += 5
        else if (specs.battery.wiredChargingWatts >= 25) batteryScore += 3
        else batteryScore += 1

        if (specs.battery.wirelessChargingWatts > 0) batteryScore += 3
        if (specs.battery.hasReverseCharging) batteryScore += 1

        val totalHardwareScore = displayScore + perfScore + camScore + batteryScore // 0..100

        // Expected price thresholds
        val normalizedPrice = max(80, priceUsd).toDouble()
        
        // Calculate Value Index (Score relative to price curve)
        val valueIndex = when (category) {
            PhoneCategory.BUDGET -> {
                val ratio = (totalHardwareScore / normalizedPrice) * 35.0
                min(9.8, max(4.0, ratio)).toFloat()
            }
            PhoneCategory.MID_RANGE -> {
                val ratio = (totalHardwareScore / (normalizedPrice * 0.7)) * 40.0
                min(9.6, max(4.5, ratio)).toFloat()
            }
            PhoneCategory.FLAGSHIP, PhoneCategory.CAMERA -> {
                val ratio = (totalHardwareScore.toDouble() / 100.0) * 8.5 + (if (normalizedPrice <= 1000) 1.0 else 0.5)
                min(9.7, max(5.0, ratio)).toFloat()
            }
            PhoneCategory.GAMING -> {
                val ratio = (perfScore + batteryScore).toDouble() / 50.0 * 8.8
                min(9.5, max(5.0, ratio)).toFloat()
            }
            else -> 7.5f
        }

        val roundedScore = Math.round(valueIndex * 10f) / 10f

        val (status, reasonEn, reasonAr) = when {
            roundedScore >= 8.0f -> {
                Triple(
                    RecommendationStatus.YES,
                    "Excellent value for money! Outstanding balance of screen quality, processing power, and battery longevity for its price class.",
                    "قيمة ممتازة مقابل السعر! توازن رائع بين جودة الشاشة، وقوة المعالج، وعمر البطارية في هذه الفئة السعرية."
                )
            }
            roundedScore >= 6.8f -> {
                Triple(
                    RecommendationStatus.DEPENDS,
                    "Solid device overall, but ideal for specific users who prioritize certain features (e.g. camera or gaming) rather than general all-rounders.",
                    "هاتف قوي بشكل عام، لكنه مناسب أكثر لمن يركز على مزايا محددة (كالكاميرا أو الألعاب) مقارنة بهواتف الفئة الشاملة."
                )
            }
            else -> {
                Triple(
                    RecommendationStatus.NO,
                    "Hard to recommend at this price point. Competing models offer significantly superior refresh rates, faster charging, or better chipsets.",
                    "يصعب التوصية به بهذا السعر، حيث تتوفر بدائل منافسة تقدم شاشات بتردد أعلى، وشحناً أسرع، ومعالجات أقوى."
                )
            }
        }

        val breakdown = ScoreBreakdown(
            displayScore = displayScore,
            performanceScore = perfScore,
            cameraScore = camScore,
            batteryScore = batteryScore,
            valueScore = roundedScore
        )

        return RecommendationVerdict(
            status = status,
            reason = LocalizedString(en = reasonEn, ar = reasonAr),
            valueScore = roundedScore,
            breakdown = breakdown
        )
    }
}
