package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Phone
import com.example.model.RecommendationStatus
import com.example.util.Strings

@Composable
fun ComparisonScreen(
    comparisonPhones: List<Phone>,
    onRemovePhone: (Phone) -> Unit,
    onClearAll: () -> Unit,
    onBrowsePhones: () -> Unit,
    isArabic: Boolean,
    modifier: Modifier = Modifier
) {
    if (comparisonPhones.size < 2) {
        // Empty or 1-phone state
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CompareArrows,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(44.dp)
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = Strings.selectPhonesToCompare(isArabic),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = Strings.compareUpTo3(isArabic),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                if (comparisonPhones.size == 1) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "1/3: ${comparisonPhones[0].name}",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            IconButton(
                                onClick = { onRemovePhone(comparisonPhones[0]) },
                                modifier = Modifier.size(20.dp)
                            ) {
                                Icon(Icons.Default.Close, contentDescription = "Remove", modifier = Modifier.size(14.dp))
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = onBrowsePhones,
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = if (isArabic) "تصفح الهواتف للاختيار" else "Browse Phones to Compare")
                }
            }
        }
        return
    }

    // Comparison Matrix Table
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        // Header actions
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${Strings.tabCompare(isArabic)} (${comparisonPhones.size}/3)",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )

            TextButton(onClick = onClearAll) {
                Icon(Icons.Default.DeleteOutline, contentDescription = null, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = Strings.clearComparison(isArabic))
            }
        }

        // Horizontal comparison scroll container
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            comparisonPhones.forEach { phone ->
                ComparisonColumn(
                    phone = phone,
                    isArabic = isArabic,
                    onRemove = { onRemovePhone(phone) },
                    modifier = Modifier.width(220.dp)
                )
            }

            if (comparisonPhones.size < 3) {
                // Add 3rd phone slot placeholder
                Card(
                    modifier = Modifier
                        .width(180.dp)
                        .height(300.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircleOutline,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (isArabic) "أضف هاتفاً ثالثاً" else "Add 3rd Phone",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            OutlinedButton(
                                onClick = onBrowsePhones,
                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = if (isArabic) "اختيار" else "Pick",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ComparisonColumn(
    phone: Phone,
    isArabic: Boolean,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    val verdictColor = when (phone.verdict.status) {
        RecommendationStatus.YES -> Color(0xFF10B981)
        RecommendationStatus.DEPENDS -> Color(0xFFF59E0B)
        RecommendationStatus.NO -> Color(0xFFEF4444)
    }

    Card(
        modifier = modifier.testTag("compare_column_${phone.id}"),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Header: Remove Button & Icon
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = phone.brand,
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                IconButton(
                    onClick = onRemove,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = phone.name,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = phone.formattedPrice,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Recommendation Badge
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = verdictColor.copy(alpha = 0.15f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = phone.verdict.status.getShort(isArabic),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = verdictColor
                        )
                    )
                    Text(
                        text = "${phone.verdict.valueScore}/10",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = verdictColor
                        )
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))

            // Category & Year
            CompareItem(
                label = Strings.filterCategory(isArabic),
                value = phone.category.getTitle(isArabic)
            )
            CompareItem(
                label = Strings.filterYear(isArabic),
                value = phone.releaseYear.toString()
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Display
            Text(
                text = Strings.specDisplay(isArabic),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            )
            CompareItem(
                label = if (isArabic) "المقاس والتردد" else "Size & Hz",
                value = "${phone.specs.display.size} • ${phone.specs.display.refreshRateHz}Hz"
            )
            CompareItem(
                label = if (isArabic) "نوع الشاشة" else "Panel",
                value = phone.specs.display.type
            )
            CompareItem(
                label = if (isArabic) "السطوع" else "Brightness",
                value = "${phone.specs.display.peakBrightnessNits} nits"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Performance
            Text(
                text = Strings.specChipset(isArabic),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            )
            CompareItem(
                label = if (isArabic) "المعالج" else "Chipset",
                value = phone.specs.chipset.name
            )
            CompareItem(
                label = if (isArabic) "الرام" else "RAM",
                value = phone.specs.memory.ramDisplay
            )
            CompareItem(
                label = if (isArabic) "التخزين" else "Storage",
                value = phone.specs.memory.storageDisplay
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Camera
            Text(
                text = Strings.specCameras(isArabic),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            )
            CompareItem(
                label = if (isArabic) "الكاميرا الخلفية" else "Rear Camera",
                value = phone.specs.cameras.rearSummary
            )
            CompareItem(
                label = if (isArabic) "الكاميرا الأمامية" else "Selfie",
                value = phone.specs.cameras.frontSummary
            )
            CompareItem(
                label = if (isArabic) "تقريب زوم" else "Telephoto",
                value = if (phone.specs.cameras.hasTelephoto) "✓ Yes" else "✗ No"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Battery
            Text(
                text = Strings.specBattery(isArabic),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            )
            CompareItem(
                label = if (isArabic) "البطارية" else "Battery",
                value = "${phone.specs.battery.capacityMah} mAh"
            )
            CompareItem(
                label = if (isArabic) "سرعة الشحن" else "Charging",
                value = "${phone.specs.battery.wiredChargingWatts} W"
            )
            CompareItem(
                label = if (isArabic) "شحن لاسلكي" else "Wireless",
                value = if (phone.specs.battery.wirelessChargingWatts > 0) "${phone.specs.battery.wirelessChargingWatts}W" else "✗"
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // Durability & OS
            Text(
                text = Strings.specBuild(isArabic),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            )
            CompareItem(
                label = if (isArabic) "مقاومة الماء" else "Water Rating",
                value = phone.specs.build.waterResistance
            )
            CompareItem(
                label = if (isArabic) "الوزن" else "Weight",
                value = "${phone.specs.build.weightGrams} g"
            )
            CompareItem(
                label = if (isArabic) "النظام" else "OS",
                value = phone.specs.os.osName
            )
        }
    }
}

@Composable
private fun CompareItem(
    label: String,
    value: String
) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Medium
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
