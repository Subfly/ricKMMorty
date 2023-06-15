package dev.subfly.rickmmorty.android.view.container.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.common.enums.CharacterGender
import dev.subfly.rickmmorty.common.enums.CharacterQueryAcceptField
import dev.subfly.rickmmorty.common.enums.CharacterStatus
import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.common.enums.EpisodeQueryAcceptField
import dev.subfly.rickmmorty.common.enums.LocationQueryAcceptField
import dev.subfly.rickmmorty.common.enums.toDisplayText
import dev.subfly.rickmmorty.common.models.CharacterFilterOptions
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.common.models.LocationFilterOptions

@Composable
fun ChangeFilterOptionsSheetContent(
    appliedFilter: ContentFilter,
    characterOptions: CharacterFilterOptions,
    episodeOptions: EpisodeFilterOptions,
    locationOptions: LocationFilterOptions,
    onCharacterOptionsChanged: (CharacterFilterOptions) -> Unit,
    onEpisodeOptionChanged: (EpisodeFilterOptions) -> Unit,
    onLocationOptionChanged: (LocationFilterOptions) -> Unit
) {
    Column {
        when (appliedFilter) {
            ContentFilter.CHARACTER -> {
                CharacterFilterOptionsContent(
                    selectedOptions = characterOptions,
                    onOptionSelected = onCharacterOptionsChanged
                )
            }

            ContentFilter.EPISODE -> {
                EpisodeFilterOptionsContent(
                    selectedOptions = episodeOptions,
                    onOptionSelected = onEpisodeOptionChanged
                )
            }

            ContentFilter.LOCATION -> {
                LocationFilterOptionsContent(
                    selectedOptions = locationOptions,
                    onOptionSelected = onLocationOptionChanged
                )
            }
        }
    }
}

@Composable
private fun CharacterFilterOptionsContent(
    selectedOptions: CharacterFilterOptions,
    onOptionSelected: (CharacterFilterOptions) -> Unit
) {
    val searchOnFields = CharacterQueryAcceptField.values()
    val genderFields = CharacterGender.values()
    val statusFields = CharacterStatus.values()

    LazyColumn(
        modifier = Modifier.fillMaxHeight(0.8F)
    ) {

        item {
            Text(
                text = "Search On",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
            )
        }
        items(searchOnFields) { field ->
            ListItem(
                headlineContent = {
                    Text(
                        text = field.toDisplayText(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    RadioButton(
                        selected = selectedOptions.searchOn == field,
                        onClick = {
                            onOptionSelected(
                                selectedOptions.copy(
                                    searchOn = field
                                )
                            )
                        }
                    )
                }
            )
        }

        item {
            Text(
                text = "Gender Filter",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
            )
        }
        items(genderFields) { field ->
            ListItem(
                headlineContent = {
                    Text(
                        text = field.toDisplayText(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    RadioButton(
                        selected = selectedOptions.gender == field,
                        onClick = {
                            onOptionSelected(
                                selectedOptions.copy(
                                    gender = field
                                )
                            )
                        }
                    )
                }
            )
        }

        item {
            Text(
                text = "Status Filter",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
            )
        }
        items(statusFields) { field ->
            ListItem(
                headlineContent = {
                    Text(
                        text = field.toDisplayText(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    RadioButton(
                        selected = selectedOptions.status == field,
                        onClick = {
                            onOptionSelected(
                                selectedOptions.copy(
                                    status = field
                                )
                            )
                        }
                    )
                }
            )
        }

        item {
            Spacer(modifier = Modifier.size(36.dp))
        }
    }

}

@Composable
private fun EpisodeFilterOptionsContent(
    selectedOptions: EpisodeFilterOptions,
    onOptionSelected: (EpisodeFilterOptions) -> Unit
) {
    val fields = EpisodeQueryAcceptField.values()
    Text(
        text = "Search On",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
    )
    fields.forEach { field ->
        ListItem(
            headlineContent = {
                Text(
                    text = field.toDisplayText(),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            trailingContent = {
                RadioButton(
                    selected = selectedOptions.searchOn == field,
                    onClick = {
                        onOptionSelected(
                            EpisodeFilterOptions(
                                searchOn = field
                            )
                        )
                    }
                )
            }
        )
    }
    Spacer(modifier = Modifier.size(36.dp))
}

@Composable
private fun LocationFilterOptionsContent(
    selectedOptions: LocationFilterOptions,
    onOptionSelected: (LocationFilterOptions) -> Unit
) {
    val fields = LocationQueryAcceptField.values()
    Text(
        text = "Search On",
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp
            )
    )
    fields.forEach { field ->
        ListItem(
            headlineContent = {
                Text(
                    text = field.toDisplayText(),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            trailingContent = {
                RadioButton(
                    selected = selectedOptions.searchOn == field,
                    onClick = {
                        onOptionSelected(
                            LocationFilterOptions(
                                searchOn = field
                            )
                        )
                    }
                )
            }
        )
    }
    Spacer(modifier = Modifier.size(36.dp))
}