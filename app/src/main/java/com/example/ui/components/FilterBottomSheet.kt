package com.example.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.model.RecommendationStatus
import com.example.util.Strings
import com.example.viewmodel.PriceFilter
import com.example.viewmodel.SortOption

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    selectedPrice: PriceFilter,
    onPriceSelect: (PriceFilter) -> Unit,
    selectedYear: Int?,
    onYearSelect: (Int?) -> Unit,
    selectedVerdict: RecommendationStatus?,
    onVerdictSelect: (RecommendationStatus?) -> Unit,
    selectedSort: SortOption,
    onSortSelect: (SortOption) -> Unit,
    onReset: () -> Unit,
    onDismiss: () -> Unit,
    isArabic: Boolean
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.filterTitle(isArabic),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                TextButton(onClick = onReset) {
                    Text(text = Strings.filterReset(isArabic))
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 1. Sorting Order
            Text(
                text = if (isArabic) "ترتيب النتائج" else "Sort By",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                SortOption.values().forEach { sort ->
                    FilterChip(
                        selected = selectedSort == sort,
                        onClick = { onSortSelect(sort) },
                        label = { Text(text = sort.getTitle(isArabic)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2. Price Range Section
            Text(
                text = Strings.filterPrice(isArabic),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PriceChip(
                    text = Strings.priceAny(isArabic),
                    selected = selectedPrice == PriceFilter.ANY,
                    onClick = { onPriceSelect(PriceFilter.ANY) },
                    modifier = Modifier.weight(1f)
                )
                PriceChip(
                    text = Strings.priceUnder200(isArabic),
                    selected = selectedPrice == PriceFilter.UNDER_200,
                    onClick = { onPriceSelect(PriceFilter.UNDER_200) },
                    modifier = Modifier.weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PriceChip(
                    text = Strings.price200to500(isArabic),
                    selected = selectedPrice == PriceFilter.BETWEEN_200_500,
                    onClick = { onPriceSelect(PriceFilter.BETWEEN_200_500) },
                    modifier = Modifier.weight(1f)
                )
                PriceChip(
                    text = Strings.price500to900(isArabic),
                    selected = selectedPrice == PriceFilter.BETWEEN_500_900,
                    onClick = { onPriceSelect(PriceFilter.BETWEEN_500_900) },
                    modifier = Modifier.weight(1f)
                )
                PriceChip(
                    text = Strings.priceOver900(isArabic),
                    selected = selectedPrice == PriceFilter.OVER_900,
                    onClick = { onPriceSelect(PriceFilter.OVER_900) },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 3. Recommendation Status Section
            Text(
                text = Strings.filterVerdict(isArabic),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                FilterChip(
                    selected = selectedVerdict == null,
                    onClick = { onVerdictSelect(null) },
                    label = { Text(text = Strings.filterAll(isArabic)) },
                    modifier = Modifier.fillMaxWidth()
                )
                FilterChip(
                    selected = selectedVerdict == RecommendationStatus.YES,
                    onClick = { onVerdictSelect(RecommendationStatus.YES) },
                    label = { Text(text = Strings.verdictRecommended(isArabic) + " (Yes)") },
                    modifier = Modifier.fillMaxWidth()
                )
                FilterChip(
                    selected = selectedVerdict == RecommendationStatus.DEPENDS,
                    onClick = { onVerdictSelect(RecommendationStatus.DEPENDS) },
                    label = { Text(text = Strings.verdictDepends(isArabic) + " (Depends)") },
                    modifier = Modifier.fillMaxWidth()
                )
                FilterChip(
                    selected = selectedVerdict == RecommendationStatus.NO,
                    onClick = { onVerdictSelect(RecommendationStatus.NO) },
                    label = { Text(text = Strings.verdictNotRecommended(isArabic) + " (No)") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 4. Release Year Section
            Text(
                text = Strings.filterYear(isArabic),
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedYear == null,
                    onClick = { onYearSelect(null) },
                    label = { Text(text = Strings.filterAll(isArabic)) }
                )
                listOf(2025, 2024, 2023).forEach { year ->
                    FilterChip(
                        selected = selectedYear == year,
                        onClick = { onYearSelect(year) },
                        label = { Text(text = year.toString()) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Apply Button
            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = Strings.filterApply(isArabic),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
private fun PriceChip(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(text = text, maxLines = 1)
        },
        modifier = modifier
    )
}
