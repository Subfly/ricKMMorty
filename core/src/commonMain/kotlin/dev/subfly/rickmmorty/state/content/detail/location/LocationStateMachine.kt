package dev.subfly.rickmmorty.state.content.detail.location

import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetLocationById
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.domain.useCases.local.location.DislikeLocation
import dev.subfly.rickmmorty.domain.useCases.local.location.LikeLocation
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class LocationStateMachine(
    scope: CoroutineScope? = null
) : BaseStateMachine<LocationEvent> (coroutineScope = scope) {

    private val getLocationUseCase: GetLocationById by inject()
    private val likeLocationUseCase: LikeLocation by inject()
    private val dislikeLocationUseCase: DislikeLocation by inject()

    private val _state = MutableStateFlow(LocationState())
    val currentState = _state.toCommonStateFlow()

    override fun onEvent(event: LocationEvent) {
        when(event) {
            is LocationEvent.InitWithId -> handleInitEvent(event)
            LocationEvent.DislikeLocation -> handleDislikeLocationEvent()
            LocationEvent.LikeLocation -> handleLikeLocationEvent()
        }
    }

    private fun handleInitEvent(event: LocationEvent.InitWithId) {
        val id = event.id
        if (id == INVALID_ID) {
            _state.update {
                it.copy(
                    error = "Can not find episode with id: $id",
                    isLoading = false,
                )
            }
        } else {
            stateMachineScope.launch {
                getLocationUseCase(id = id).collect { resource ->
                    _state.update {
                        it.copy(
                            isLoading = resource == Resource.Loading,
                            error = if (resource is Resource.Error) resource.message else "",
                            data = if (resource is Resource.Success) resource.data else it.data
                        )
                    }
                }
            }
        }
    }

    private fun handleLikeLocationEvent() {
        _state.value.data?.let { model ->
            stateMachineScope.launch {
                likeLocationUseCase(model = model)
            }
        }
    }

    private fun handleDislikeLocationEvent() {
        _state.value.data?.let { model ->
            stateMachineScope.launch {
                dislikeLocationUseCase(id = model.id)
            }
        }
    }

}