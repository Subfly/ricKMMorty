package dev.subfly.rickmmorty.common.enums

enum class CharacterGender(val value: String) {
    FEMALE("female"),
    MALE("male"),
    GENDERLESS("genderless"),
    UNKNOWN("unknown"),
    NONE("")
}

fun CharacterGender.toDisplayText(): String {
    return when(this) {
        CharacterGender.NONE -> "None"
        else -> this.value.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}

fun String?.toCharacterGender(): CharacterGender {
    return when(this) {
        "Female" -> CharacterGender.FEMALE
        "Male" -> CharacterGender.MALE
        "Genderless" -> CharacterGender.GENDERLESS
        "Unknown" -> CharacterGender.UNKNOWN
        else -> CharacterGender.NONE
    }
}