package dev.subfly.rickmmorty.state.content.detail.episode

import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.state.base.BaseState

data class EpisodeState(
    override val isLoading: Boolean = true,
    override val error: String = "",
    override val data: EpisodeModel? = null
): BaseState<EpisodeModel?>