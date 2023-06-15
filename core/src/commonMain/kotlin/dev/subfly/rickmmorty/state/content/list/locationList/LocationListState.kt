package dev.subfly.rickmmorty.state.content.list.locationList

import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.state.base.BaseState

data class LocationListState(
    override val isLoading: Boolean = true,
    override val error: String = "",
    override val data: List<LocationModel> = emptyList()
): BaseState<List<LocationModel>>