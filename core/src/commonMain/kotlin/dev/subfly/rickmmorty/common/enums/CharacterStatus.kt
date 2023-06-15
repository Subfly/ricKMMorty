package dev.subfly.rickmmorty.common.enums

enum class CharacterStatus(
    val value: String
) {
    ALIVE("alive"),
    DEAD("dead"),
    UNKNOWN("unknown"),
    NONE("")
}

fun CharacterStatus.toDisplayText(): String {
    return when(this) {
        CharacterStatus.NONE -> "None"
        else -> this.value.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase() else it.toString()
        }
    }
}

fun String?.toCharacterStatus(): CharacterStatus {
    return when(this?.lowercase()) {
        "alive" -> CharacterStatus.ALIVE
        "dead" -> CharacterStatus.DEAD
        "unknown" -> CharacterStatus.UNKNOWN
        else -> CharacterStatus.NONE
    }
}