package dev.subfly.rickmmorty.common.models

import dev.subfly.rickmmorty.common.enums.EpisodeQueryAcceptField

data class EpisodeFilterOptions(
    val searchOn: EpisodeQueryAcceptField = EpisodeQueryAcceptField.NAME
)
