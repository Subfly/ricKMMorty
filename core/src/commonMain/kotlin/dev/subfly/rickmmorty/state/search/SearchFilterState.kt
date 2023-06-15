package dev.subfly.rickmmorty.state.search

import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.common.models.CharacterFilterOptions
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.common.models.LocationFilterOptions

data class SearchFilterState(
    val appliedFilter: ContentFilter = ContentFilter.CHARACTER,
    val characterFilterOptions: CharacterFilterOptions = CharacterFilterOptions(),
    val episodeFilterOptions: EpisodeFilterOptions = EpisodeFilterOptions(),
    val locationFilterOptions: LocationFilterOptions = LocationFilterOptions()
) {

}
