package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Phone
import com.example.model.RecommendationStatus
import com.example.ui.components.PhoneImageView
import com.example.ui.components.TikTokBanner
import com.example.util.CurrencyHelper
import com.example.util.CurrencyMode
import com.example.util.Strings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneDetailScreen(
    phone: Phone,
    isArabic: Boolean,
    isCompared: Boolean,
    isFavorite: Boolean,
    currencyMode: CurrencyMode,
    dzdRate: Double,
    onCompareToggle: (Phone) -> Unit,
    onFavoriteToggle: (Phone) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onBack() }

    val verdictColor = when (phone.verdict.status) {
        RecommendationStatus.YES -> Color(0xFF10B981) // Green
        RecommendationStatus.DEPENDS -> Color(0xFFF59E0B) // Amber
        RecommendationStatus.NO -> Color(0xFFEF4444) // Red
    }

    val formattedPrice = CurrencyHelper.formatPrice(
        priceUsd = phone.approxPriceUsd,
        dzdRate = dzdRate,
        mode = currencyMode,
        isArabic = isArabic
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = phone.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("detail_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { onFavoriteToggle(phone) },
                        modifier = Modifier.testTag("detail_fav_toggle")
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(
                        onClick = { onCompareToggle(phone) },
                        modifier = Modifier.testTag("detail_compare_toggle")
                    ) {
                        Icon(
                            imageVector = if (isCompared) Icons.Default.CheckCircle else Icons.Default.CompareArrows,
                            contentDescription = "Compare",
                            tint = if (isCompared) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            // Hero Device Header Card with Coil Image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Phone Image with Coil
                    PhoneImageView(
                        phone = phone,
                        modifier = Modifier.size(160.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = phone.name,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Text(
                                text = phone.brand,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.secondaryContainer
                        ) {
                            Text(
                                text = phone.category.getTitle(isArabic),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "• ${phone.releaseYear}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Price Banner (USD & DZD)
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    ) {
                        Text(
                            text = formattedPrice,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 1. "DO WE RECOMMEND IT?" Highlighted Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("verdict_card")
                    .border(1.5.dp, verdictColor.copy(alpha = 0.5f), RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(
                    containerColor = verdictColor.copy(alpha = 0.08f)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = when (phone.verdict.status) {
                                    RecommendationStatus.YES -> Icons.Default.CheckCircle
                                    RecommendationStatus.DEPENDS -> Icons.Default.Info
                                    RecommendationStatus.NO -> Icons.Default.Cancel
                                },
                                contentDescription = null,
                                tint = verdictColor,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = Strings.verdictQuestion(isArabic),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Surface(
                            shape = CircleShape,
                            color = verdictColor
                        ) {
                            Text(
                                text = phone.verdict.status.getShort(isArabic),
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.ExtraBold),
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = phone.verdict.reason.get(isArabic),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            lineHeight = 22.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Value Score Progress Indicator
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = Strings.scoreOutOfTen(isArabic),
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = "${phone.verdict.valueScore} / 10",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = verdictColor
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    val animatedProgress by animateFloatAsState(
                        targetValue = (phone.verdict.valueScore / 10f).coerceIn(0f, 1f),
                        label = "scoreProgress"
                    )

                    LinearProgressIndicator(
                        progress = { animatedProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = verdictColor,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Pros & Cons Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Pros Column
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF10B981).copy(alpha = 0.08f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ThumbUp,
                                contentDescription = null,
                                tint = Color(0xFF10B981),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = Strings.prosTitle(isArabic),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF10B981)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        phone.pros.forEach { pro ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("✓ ", color = Color(0xFF10B981), fontWeight = FontWeight.Bold)
                                Text(
                                    text = pro.get(isArabic),
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp)
                                )
                            }
                        }
                    }
                }

                // Cons Column
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEF4444).copy(alpha = 0.08f)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.ThumbDown,
                                contentDescription = null,
                                tint = Color(0xFFEF4444),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = Strings.consTitle(isArabic),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFFEF4444)
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        phone.cons.forEach { con ->
                            Row(
                                modifier = Modifier.padding(vertical = 3.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text("✗ ", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold)
                                Text(
                                    text = con.get(isArabic),
                                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.5.sp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 3. FULL TECHNICAL SPECIFICATIONS
            Text(
                text = Strings.specsSection(isArabic),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 6.dp)
            )

            // Spec Group 1: Display
            SpecGroupCard(
                title = Strings.specDisplay(isArabic),
                icon = Icons.Default.Smartphone
            ) {
                SpecRow(label = if (isArabic) "حجم الشاشة" else "Size", value = phone.specs.display.size)
                SpecRow(label = if (isArabic) "نوع الشاشة" else "Panel Type", value = phone.specs.display.type)
                SpecRow(label = if (isArabic) "تردد التحديث" else "Refresh Rate", value = "${phone.specs.display.refreshRateHz} Hz")
                SpecRow(label = if (isArabic) "الدقة" else "Resolution", value = phone.specs.display.resolution)
                SpecRow(label = if (isArabic) "ذروة السطوع" else "Peak Brightness", value = "${phone.specs.display.peakBrightnessNits} nits")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spec Group 2: Chipset & Performance
            SpecGroupCard(
                title = Strings.specChipset(isArabic),
                icon = Icons.Default.Memory
            ) {
                SpecRow(label = if (isArabic) "المعالج" else "Chipset", value = phone.specs.chipset.name)
                SpecRow(label = if (isArabic) "المعمارية والأنوية" else "CPU", value = phone.specs.chipset.cpu)
                SpecRow(label = if (isArabic) "معالج الرسوميات" else "GPU", value = phone.specs.chipset.gpu)
                SpecRow(label = if (isArabic) "دقة التصنيع" else "Process Node", value = phone.specs.chipset.processNode)
                SpecRow(label = if (isArabic) "فئة الأداء" else "Tier", value = phone.specs.chipset.scoreTier)
                SpecRow(label = if (isArabic) "خيارات الرام" else "RAM Options", value = phone.specs.memory.ramDisplay)
                SpecRow(label = if (isArabic) "سعات التخزين" else "Storage Options", value = phone.specs.memory.storageDisplay)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spec Group 3: Cameras
            SpecGroupCard(
                title = Strings.specCameras(isArabic),
                icon = Icons.Default.CameraAlt
            ) {
                SpecRow(label = if (isArabic) "الكاميرات الخلفية" else "Rear Camera", value = phone.specs.cameras.rearSummary)
                SpecRow(label = if (isArabic) "الكاميرا الأمامية" else "Front Selfie", value = phone.specs.cameras.frontSummary)
                SpecRow(label = if (isArabic) "تصوير الفيديو" else "Video Recording", value = phone.specs.cameras.videoRecording)
                SpecRow(label = if (isArabic) "تثبيت بصري OIS" else "OIS Support", value = if (phone.specs.cameras.hasOis) "✓ Yes" else "✗ No")
                SpecRow(label = if (isArabic) "كاميرا تقريب زوم" else "Telephoto Lens", value = if (phone.specs.cameras.hasTelephoto) "✓ Yes" else "✗ No")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spec Group 4: Battery & Charging
            SpecGroupCard(
                title = Strings.specBattery(isArabic),
                icon = Icons.Default.BatteryChargingFull
            ) {
                SpecRow(label = if (isArabic) "سعة البطارية" else "Capacity", value = "${phone.specs.battery.capacityMah} mAh")
                SpecRow(label = if (isArabic) "سرعة الشحن السلكي" else "Wired Charging", value = "${phone.specs.battery.wiredChargingWatts} W")
                SpecRow(
                    label = if (isArabic) "الشحن اللاسلكي" else "Wireless Charging",
                    value = if (phone.specs.battery.wirelessChargingWatts > 0) "${phone.specs.battery.wirelessChargingWatts} W" else if (isArabic) "غير مدعوم" else "Not Supported"
                )
                SpecRow(
                    label = if (isArabic) "الشحن العكسي" else "Reverse Charging",
                    value = if (phone.specs.battery.hasReverseCharging) "✓ Yes" else "✗ No"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spec Group 5: OS & Connectivity
            SpecGroupCard(
                title = Strings.specConnectivity(isArabic),
                icon = Icons.Default.Wifi
            ) {
                SpecRow(label = if (isArabic) "نظام التشغيل" else "OS", value = phone.specs.os.osName)
                SpecRow(label = if (isArabic) "واجهة المستخدم" else "UI Skin", value = phone.specs.os.uiSkin)
                SpecRow(label = if (isArabic) "سنوات التحديثات" else "Update Support", value = "${phone.specs.os.supportYears} ${if (isArabic) "سنوات" else "Years"}")
                SpecRow(label = "5G", value = if (phone.specs.connectivity.has5g) "✓ 5G Enabled" else "4G LTE")
                SpecRow(label = "Wi-Fi", value = phone.specs.connectivity.wifiStandard)
                SpecRow(label = "Bluetooth", value = phone.specs.connectivity.bluetoothVersion)
                SpecRow(label = "NFC", value = if (phone.specs.connectivity.hasNfc) "✓ Yes" else "✗ No")
                SpecRow(label = "USB", value = phone.specs.connectivity.usbType)
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Spec Group 6: Durability & Build
            SpecGroupCard(
                title = Strings.specBuild(isArabic),
                icon = Icons.Default.Shield
            ) {
                SpecRow(label = if (isArabic) "مقاومة الماء والغبار" else "Water/Dust Rating", value = phone.specs.build.waterResistance)
                SpecRow(label = if (isArabic) "الوزن" else "Weight", value = "${phone.specs.build.weightGrams} g")
                SpecRow(label = if (isArabic) "الأبعاد" else "Dimensions", value = phone.specs.build.dimensionsMm)
                SpecRow(label = if (isArabic) "مواد التصنيع" else "Materials", value = phone.specs.build.materials)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // TikTok Owner Promotion Banner
            TikTokBanner(isArabic = isArabic)

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SpecGroupCard(
    title: String,
    icon: ImageVector,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            )
            content()
        }
    }
}

@Composable
private fun SpecRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant),
            modifier = Modifier.weight(1.1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1.4f)
        )
    }
}
