package com.example.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ApiConfig
import com.example.data.SyncStatus
import com.example.ui.components.openTikTokProfile
import com.example.util.Strings
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SettingsDialog(
    currentApiUrl: String,
    syncStatus: SyncStatus,
    onSaveApiUrl: (String) -> Unit,
    onSyncNow: () -> Unit,
    onDismiss: () -> Unit,
    isArabic: Boolean
) {
    val context = LocalContext.current
    var inputUrl by remember(currentApiUrl) { mutableStateOf(currentApiUrl) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.CloudSync,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = Strings.dataSourceConfig(isArabic),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = Strings.autoRefreshNote(isArabic),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 18.sp
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // API URL Field
                OutlinedTextField(
                    value = inputUrl,
                    onValueChange = { inputUrl = it },
                    label = { Text(Strings.apiUrlLabel(isArabic)) },
                    placeholder = { Text(ApiConfig.DEFAULT_API_URL) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("api_url_input"),
                    singleLine = false,
                    maxLines = 3,
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = Strings.apiUrlHint(isArabic),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 11.sp
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Sync Status Badge
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            when (syncStatus) {
                                is SyncStatus.Syncing -> {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = Strings.syncing(isArabic),
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }
                                is SyncStatus.Success -> {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "${Strings.syncSuccess(isArabic)} (${syncStatus.count} phones)",
                                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                }
                                is SyncStatus.Fallback -> {
                                    Icon(
                                        imageVector = Icons.Default.OfflinePin,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = Strings.syncFallback(isArabic),
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    )
                                }
                                SyncStatus.Idle -> {
                                    Icon(
                                        imageVector = Icons.Default.CloudQueue,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (isArabic) "جاهز للمزامنة" else "Ready to sync",
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                }
                            }
                        }

                        val timeString = remember(syncStatus) {
                            val ts = when (syncStatus) {
                                is SyncStatus.Success -> syncStatus.timestamp
                                is SyncStatus.Fallback -> syncStatus.timestamp
                                else -> System.currentTimeMillis()
                            }
                            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date(ts))
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${if (isArabic) "آخر فحص: " else "Last Check: "} $timeString",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 10.5.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Sync Now Button
                FilledTonalButton(
                    onClick = onSyncNow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("sync_now_button"),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = Strings.syncNow(isArabic))
                }

                Spacer(modifier = Modifier.height(14.dp))

                // TikTok Creator Button
                OutlinedButton(
                    onClick = { openTikTokProfile(context) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = Strings.tiktokButton(isArabic))
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onSaveApiUrl(inputUrl)
                    onDismiss()
                },
                modifier = Modifier.testTag("save_api_settings_button")
            ) {
                Text(text = if (isArabic) "حفظ وإغلاق" else "Save & Close")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = if (isArabic) "إلغاء" else "Cancel")
            }
        }
    )
}
