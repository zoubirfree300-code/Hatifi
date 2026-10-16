package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.PhoneDetailScreen
import com.example.ui.theme.HatifiTheme
import com.example.util.AppLanguage
import com.example.viewmodel.HatifiViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: HatifiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()
            val appLanguage by viewModel.appLanguage.collectAsState()
            val selectedPhoneDetail by viewModel.selectedPhoneDetail.collectAsState()
            val comparisonList by viewModel.comparisonList.collectAsState()
            val favoritesList by viewModel.favoritesList.collectAsState()
            val currencyMode by viewModel.currencyMode.collectAsState()
            val dzdRate by viewModel.dzdRate.collectAsState()

            val isArabic = appLanguage == AppLanguage.ARABIC
            val layoutDirection = if (isArabic) LayoutDirection.Rtl else LayoutDirection.Ltr

            HatifiTheme(darkTheme = isDarkTheme) {
                CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        if (selectedPhoneDetail != null) {
                            val phone = selectedPhoneDetail!!
                            PhoneDetailScreen(
                                phone = phone,
                                isArabic = isArabic,
                                isCompared = comparisonList.any { it.id == phone.id },
                                isFavorite = favoritesList.any { it.id == phone.id },
                                currencyMode = currencyMode,
                                dzdRate = dzdRate,
                                onCompareToggle = { viewModel.toggleComparison(it) },
                                onFavoriteToggle = { viewModel.toggleFavorite(it) },
                                onBack = { viewModel.closePhoneDetail() }
                            )
                        } else {
                            HomeScreen(
                                viewModel = viewModel,
                                isArabic = isArabic
                            )
                        }
                    }
                }
            }
        }
    }
}
