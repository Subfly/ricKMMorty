package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.common.enums.ContentFilter

@Composable
fun ChangeFilterSheetContent(
    currentFilter: ContentFilter,
    onSelectFilter: (ContentFilter) -> Unit
) {
    val availableFilters = ContentFilter.values()
    Column {
        Text(
            text = "Select a Filter",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 12.dp,
                    horizontal = 20.dp
                )
        )
        availableFilters.forEach { filterType ->
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(
                            id = when(filterType) {
                                ContentFilter.CHARACTER -> R.drawable.ic_character
                                ContentFilter.EPISODE -> R.drawable.ic_episode
                                ContentFilter.LOCATION -> R.drawable.ic_location
                            }
                        ),
                        contentDescription = "${filterType.toDisplayText()} Icon"
                    )
                },
                headlineContent = {
                    Text(
                        text = filterType.toDisplayText(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    RadioButton(
                        selected = currentFilter == filterType,
                        onClick = {
                            onSelectFilter(filterType)
                        }
                    )
                }
            )
        }
        Spacer(modifier = Modifier.size(36.dp))
    }
}
