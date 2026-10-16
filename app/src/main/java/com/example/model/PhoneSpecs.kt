package com.example.model

data class DisplaySpecs(
    val size: String,
    val type: String,
    val refreshRateHz: Int,
    val resolution: String,
    val peakBrightnessNits: Int
)

data class ChipsetSpecs(
    val name: String,
    val cpu: String,
    val gpu: String,
    val processNode: String,
    val scoreTier: String
)

data class MemorySpecs(
    val ramOptions: List<String>,
    val storageOptions: List<String>
) {
    val ramDisplay: String get() = ramOptions.joinToString(" / ")
    val storageDisplay: String get() = storageOptions.joinToString(" / ")
}

data class CameraSpecs(
    val rearSummary: String,
    val frontSummary: String,
    val videoRecording: String,
    val mainSensorMp: Int,
    val hasOis: Boolean = true,
    val hasTelephoto: Boolean = false
)

data class BatterySpecs(
    val capacityMah: Int,
    val wiredChargingWatts: Int,
    val wirelessChargingWatts: Int = 0,
    val hasReverseCharging: Boolean = false
)

data class OsSpecs(
    val osName: String,
    val uiSkin: String,
    val supportYears: Int
)

data class ConnectivitySpecs(
    val has5g: Boolean = true,
    val wifiStandard: String,
    val bluetoothVersion: String,
    val hasNfc: Boolean = true,
    val hasUwb: Boolean = false,
    val usbType: String
)

data class BuildSpecs(
    val waterResistance: String,
    val dimensionsMm: String,
    val weightGrams: Int,
    val materials: String
)

data class ScoreBreakdown(
    val displayScore: Int,
    val performanceScore: Int,
    val cameraScore: Int,
    val batteryScore: Int,
    val valueScore: Float
)

data class RecommendationVerdict(
    val status: RecommendationStatus,
    val reason: LocalizedString,
    val valueScore: Float,
    val breakdown: ScoreBreakdown? = null
)

data class PhoneSpecs(
    val display: DisplaySpecs,
    val chipset: ChipsetSpecs,
    val memory: MemorySpecs,
    val cameras: CameraSpecs,
    val battery: BatterySpecs,
    val os: OsSpecs,
    val connectivity: ConnectivitySpecs,
    val build: BuildSpecs
)
