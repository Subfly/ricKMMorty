package dev.subfly.rickmmorty.android.view.container.liked.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.android.view.container.liked.LikedContentUIEvent

@OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun LikedContentFiltersView(
    appliedFilters: List<ContentFilter>,
    onClickFilter: (LikedContentUIEvent) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = ContentFilter.CHARACTER in appliedFilters,
            onClick = {
                onClickFilter(LikedContentUIEvent.CharacterFilterPressed)
            },
            label = {
                Text(
                    text = "Characters",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        )
        FilterChip(
            selected = ContentFilter.EPISODE in appliedFilters,
            onClick = {
                onClickFilter(LikedContentUIEvent.EpisodeFilterPressed)
            },
            label = {
                Text(
                    text = "Episodes",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        )
        FilterChip(
            selected = ContentFilter.LOCATION in appliedFilters,
            onClick = {
                onClickFilter(LikedContentUIEvent.LocationFilterPressed)
            },
            label = {
                Text(
                    text = "Locations",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        )
    }
}