package dev.subfly.rickmmorty.common.models

import dev.subfly.rickmmorty.common.enums.CharacterGender
import dev.subfly.rickmmorty.common.enums.CharacterQueryAcceptField
import dev.subfly.rickmmorty.common.enums.CharacterStatus

data class CharacterFilterOptions(
    val status: CharacterStatus = CharacterStatus.NONE,
    val gender: CharacterGender = CharacterGender.NONE,
    val searchOn: CharacterQueryAcceptField = CharacterQueryAcceptField.NAME
)
