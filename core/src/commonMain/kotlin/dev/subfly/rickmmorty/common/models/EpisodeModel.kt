package dev.subfly.rickmmorty.common.models

data class EpisodeModel(
    val id: Int = -1,
    val name: String = "",
    val airDate: String = "",
    val episode: String = "",
    val characters: List<CharacterModel> = emptyList()
)
