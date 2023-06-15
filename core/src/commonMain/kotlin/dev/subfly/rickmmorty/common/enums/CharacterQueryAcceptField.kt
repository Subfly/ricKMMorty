package dev.subfly.rickmmorty.common.enums

enum class CharacterQueryAcceptField {
    NAME,
    TYPE,
    SPECIES
}

fun CharacterQueryAcceptField.toDisplayText(): String {
    return when(this) {
        CharacterQueryAcceptField.NAME -> "Name"
        CharacterQueryAcceptField.TYPE -> "Type"
        CharacterQueryAcceptField.SPECIES -> "Species"
    }
}