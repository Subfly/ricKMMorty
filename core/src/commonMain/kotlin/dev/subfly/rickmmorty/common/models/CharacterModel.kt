package dev.subfly.rickmmorty.common.models

import dev.subfly.rickmmorty.common.enums.CharacterGender
import dev.subfly.rickmmorty.common.enums.CharacterStatus


data class CharacterModel(
    val id: Int = -1,
    val name: String = "",
    val status: CharacterStatus = CharacterStatus.UNKNOWN,
    val species: String = "",
    val type: String = "",
    val gender: CharacterGender = CharacterGender.UNKNOWN,
    val imageUrl: String = "",
    val originLocation: LocationModel? = null,
    val lastLocation: LocationModel? = null,
    val episodes: List<EpisodeModel> = emptyList()
)
