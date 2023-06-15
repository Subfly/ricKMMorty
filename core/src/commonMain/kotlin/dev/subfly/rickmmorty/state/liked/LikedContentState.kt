package dev.subfly.rickmmorty.state.liked

import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.LocationModel

data class LikedContentState(

    val isLoading: Boolean = false,

    val likedCharacters: List<CharacterModel> = emptyList(),
    val likedCharacterIds: List<Int> = emptyList(),

    val likedEpisodes: List<EpisodeModel> = emptyList(),
    val likedEpisodeIds: List<Int> = emptyList(),

    val likedLocations: List<LocationModel> = emptyList(),
    val likedLocationIds: List<Int> = emptyList()

)
