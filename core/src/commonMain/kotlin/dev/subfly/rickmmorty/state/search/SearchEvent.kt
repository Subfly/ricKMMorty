package dev.subfly.rickmmorty.state.search

import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.common.models.CharacterFilterOptions
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.common.models.LocationFilterOptions

sealed class SearchEvent {

    data class OnFilterChanged(
        val newFilter: ContentFilter
    ): SearchEvent()

    data class OnCharacterFilterOptionsChanged(
        val newOptions: CharacterFilterOptions
    ): SearchEvent()

    data class OnEpisodeFilterOptionsChanged(
        val newOptions: EpisodeFilterOptions
    ): SearchEvent()

    data class OnLocationFilterOptionsChanged(
        val newOptions: LocationFilterOptions
    ): SearchEvent()

    data class OnQueryChanged(
        val newQuery: String
    ): SearchEvent()

    object LoadMore: SearchEvent()

}