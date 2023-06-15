package dev.subfly.rickmmorty.android.view.container.liked.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.android.view.container.liked.LikedContentUIEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikedContentTopBar(
    appliedFilters: List<ContentFilter>,
    onClickWubbaLubbaDubDub: () -> Unit,
    onClickFilter: (LikedContentUIEvent) -> Unit
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Favorites",
                    style = MaterialTheme.typography.titleLarge
                )
            },
            actions = {
                IconButton(
                    onClick = onClickWubbaLubbaDubDub
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "Delete All",
                    )
                }
            }
        )
        LikedContentFiltersView(
            appliedFilters = appliedFilters,
            onClickFilter = onClickFilter
        )
    }
}