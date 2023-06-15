package dev.subfly.rickmmorty.state.content.detail.location

import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.state.base.BaseState

data class LocationState(
    override val isLoading: Boolean = true,
    override val error: String = "",
    override val data: LocationModel? = null
): BaseState<LocationModel?>