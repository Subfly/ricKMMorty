package dev.subfly.rickmmorty.common.enums

enum class EpisodeQueryAcceptField {
    NAME,
    EPISODE_CODE
}

fun EpisodeQueryAcceptField.toDisplayText(): String {
    return when(this) {
        EpisodeQueryAcceptField.NAME -> "Name"
        EpisodeQueryAcceptField.EPISODE_CODE -> "Episode Code"
    }
}
