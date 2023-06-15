package dev.subfly.rickmmorty.android.view.container.liked

import dev.subfly.rickmmorty.common.enums.ContentFilter

data class LikedContentUIState(
    val appliedFilters: List<ContentFilter> = emptyList()
)
