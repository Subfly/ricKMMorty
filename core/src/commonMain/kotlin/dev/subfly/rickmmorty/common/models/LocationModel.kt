package dev.subfly.rickmmorty.common.models

data class LocationModel(
    val id: Int = -1,
    val name: String = "",
    val type: String = "",
    val dimension: String = "",
    val residents: List<CharacterModel> = emptyList()
)
