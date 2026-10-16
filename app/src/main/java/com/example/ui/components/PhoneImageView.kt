package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.model.Phone

@Composable
fun PhoneImageView(
    phone: Phone,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    val parsedColor = try {
        Color(android.graphics.Color.parseColor(phone.accentColorHex))
    } catch (e: Exception) {
        MaterialTheme.colorScheme.primary
    }

    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(phone.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = phone.name,
        contentScale = contentScale,
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        loading = {
            BrandPlaceholder(
                brand = phone.brand,
                accentColor = parsedColor,
                modifier = Modifier.fillMaxSize()
            )
        },
        error = {
            BrandPlaceholder(
                brand = phone.brand,
                accentColor = parsedColor,
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}

@Composable
fun BrandPlaceholder(
    brand: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                Brush.linearGradient(
                    listOf(
                        accentColor.copy(alpha = 0.20f),
                        accentColor.copy(alpha = 0.05f)
                    )
                )
            )
            .border(1.dp, accentColor.copy(alpha = 0.35f), RoundedCornerShape(16.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.PhoneAndroid,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = brand.uppercase(),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.5.sp,
                    fontSize = 12.sp
                ),
                color = accentColor
            )
        }
    }
}
