package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ApiConfig
import com.example.util.Strings

@Composable
fun TikTokBanner(
    isArabic: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val tiktokBorderBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFFFE2C55), // TikTok Crimson
            Color(0xFF25F4EE), // TikTok Aqua
            Color(0xFF8B5CF6)  // Violet
        )
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("tiktok_promo_banner")
            .clip(RoundedCornerShape(18.dp))
            .border(1.5.dp, tiktokBorderBrush, RoundedCornerShape(18.dp))
            .clickable {
                openTikTokProfile(context)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.65f)
        ),
        shape = RoundedCornerShape(18.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon Badge with TikTok-inspired gradient
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF000000), Color(0xFF1E293B))
                        )
                    )
                    .border(1.dp, Color(0xFFFE2C55), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "TikTok",
                    tint = Color(0xFF25F4EE),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = Strings.tiktokFollow(isArabic),
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Verified,
                        contentDescription = "Verified Creator",
                        tint = Color(0xFF20D5EC),
                        modifier = Modifier.size(15.dp)
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = Strings.tiktokSubtitle(isArabic),
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 11.5.sp,
                        lineHeight = 15.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Follow Button
            Button(
                onClick = { openTikTokProfile(context) },
                modifier = Modifier
                    .height(38.dp)
                    .testTag("follow_tiktok_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFE2C55)
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = Strings.tiktokButton(isArabic),
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}

fun openTikTokProfile(context: Context) {
    val webUri = Uri.parse(ApiConfig.TIKTOK_URL)
    try {
        // Try TikTok official app packages
        val appIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
            setPackage("com.zhiliaoapp.musically")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(appIntent)
    } catch (e: Exception) {
        try {
            val altIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
                setPackage("com.ss.android.ugc.trill")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(altIntent)
        } catch (e2: Exception) {
            val browserIntent = Intent(Intent.ACTION_VIEW, webUri).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(browserIntent)
        }
    }
}
