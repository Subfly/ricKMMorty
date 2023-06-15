package dev.subfly.rickmmorty.state.search

import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.LocationModel

data class SearchState(
    val isLoading: Boolean = false,
    val error: String = "",
    val searchedCharacters: List<CharacterModel> = emptyList(),
    val searchedEpisodes: List<EpisodeModel> = emptyList(),
    val searchedLocations: List<LocationModel> = emptyList()
)
