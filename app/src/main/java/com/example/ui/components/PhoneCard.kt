package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Phone
import com.example.model.RecommendationStatus
import com.example.util.CurrencyHelper
import com.example.util.CurrencyMode
import com.example.util.Strings

@Composable
fun PhoneCard(
    phone: Phone,
    isArabic: Boolean,
    isCompared: Boolean,
    isFavorite: Boolean,
    currencyMode: CurrencyMode,
    dzdRate: Double,
    onPhoneClick: (Phone) -> Unit,
    onCompareToggle: (Phone) -> Unit,
    onFavoriteToggle: (Phone) -> Unit,
    modifier: Modifier = Modifier
) {
    val verdictColor = when (phone.verdict.status) {
        RecommendationStatus.YES -> Color(0xFF10B981) // Emerald Green
        RecommendationStatus.DEPENDS -> Color(0xFFF59E0B) // Amber
        RecommendationStatus.NO -> Color(0xFFEF4444) // Coral Red
    }

    val formattedPrice = CurrencyHelper.formatPrice(
        priceUsd = phone.approxPriceUsd,
        dzdRate = dzdRate,
        mode = currencyMode,
        isArabic = isArabic
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("phone_card_${phone.id}")
            .clip(RoundedCornerShape(20.dp))
            .clickable { onPhoneClick(phone) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            // Top Row: Brand & Category Pills + Favorite Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Brand Badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        modifier = Modifier.padding(end = 6.dp)
                    ) {
                        Text(
                            text = phone.brand,
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    // Category Badge
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant
                    ) {
                        Text(
                            text = phone.category.getTitle(isArabic),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    if (phone.isLatest) {
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = Color(0xFFE11D48).copy(alpha = 0.15f)
                        ) {
                            Text(
                                text = if (isArabic) "جديد" else "NEW",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFE11D48)
                                ),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                // Favorite Toggle Button
                IconButton(
                    onClick = { onFavoriteToggle(phone) },
                    modifier = Modifier
                        .size(32.dp)
                        .testTag("fav_button_${phone.id}")
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color(0xFFEF4444) else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Main Info: Phone Image/Placeholder + Name + Price
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Coil Image with Brand placeholder
                PhoneImageView(
                    phone = phone,
                    modifier = Modifier.size(68.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = phone.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "${phone.releaseYear} • ${phone.specs.chipset.name}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // Price Tag in configured currency (USD / DZD / Both)
                    Text(
                        text = formattedPrice,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Recommendation Verdict Banner
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = verdictColor.copy(alpha = 0.12f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
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
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = phone.verdict.status.getTitle(isArabic),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = verdictColor
                        )
                    }

                    // Score pill
                    Surface(
                        shape = CircleShape,
                        color = verdictColor.copy(alpha = 0.25f)
                    ) {
                        Text(
                            text = "${phone.verdict.valueScore} / 10",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold),
                            color = verdictColor,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Specs Quick Badges Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                SpecChip(
                    icon = Icons.Default.Smartphone,
                    text = "${phone.specs.display.size} • ${phone.specs.display.refreshRateHz}Hz",
                    modifier = Modifier.weight(1f)
                )
                SpecChip(
                    icon = Icons.Default.CameraAlt,
                    text = "${phone.specs.cameras.mainSensorMp}MP ${if (phone.specs.cameras.hasOis) "OIS" else ""}",
                    modifier = Modifier.weight(1f)
                )
                SpecChip(
                    icon = Icons.Default.BatteryChargingFull,
                    text = "${phone.specs.battery.capacityMah}mAh • ${phone.specs.battery.wiredChargingWatts}W",
                    modifier = Modifier.weight(1.2f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Bottom Actions: Compare Checkbox + Detail Prompt
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = isCompared,
                    onClick = { onCompareToggle(phone) },
                    label = {
                        Text(
                            text = if (isCompared) Strings.removeFromCompare(isArabic) else Strings.addToCompare(isArabic),
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = if (isCompared) Icons.Default.Check else Icons.Default.CompareArrows,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.height(34.dp)
                )

                TextButton(
                    onClick = { onPhoneClick(phone) },
                    contentPadding = PaddingValues(horizontal = 8.dp)
                ) {
                    Text(
                        text = if (isArabic) "المواصفات الكاملة ←" else "Full Specs →",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
            }
        }
    }
}

@Composable
private fun SpecChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
