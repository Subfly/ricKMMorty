package dev.subfly.rickmmorty.common.enums

enum class ContentFilter {

    CHARACTER,
    EPISODE,
    LOCATION;

    fun toDisplayText() = when(this) {
        CHARACTER -> "Character"
        EPISODE -> "Episode"
        LOCATION -> "Location"
    }

}