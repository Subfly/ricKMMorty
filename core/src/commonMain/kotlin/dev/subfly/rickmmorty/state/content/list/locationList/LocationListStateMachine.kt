package dev.subfly.rickmmorty.state.content.list.locationList

import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetAllLocations
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class LocationListStateMachine(
    scope: CoroutineScope? = null
) : BaseStateMachine<LocationListEvent>(coroutineScope = scope) {

    private val getAllCharactersUseCase: GetAllLocations by inject()

    private val _currentPage = MutableStateFlow(1)

    private val _state = MutableStateFlow(LocationListState())
    val currentState = _state.asStateFlow().toCommonStateFlow()

    init {
        stateMachineScope.launch {
            _currentPage.collect { currentPage ->
                getAllCharactersUseCase(page = currentPage).collect { resource ->
                    _state.update {
                        it.copy(
                            isLoading = resource == Resource.Loading,
                            error = if (resource is Resource.Error) resource.message else "",
                            data = if (resource is Resource.Success) {
                                val currentData = it.data
                                if (currentData.isEmpty()) {
                                    resource.data
                                } else {
                                    currentData + resource.data
                                }
                            } else {
                                it.data
                            }
                        )
                    }
                }
            }
        }
    }

    override fun onEvent(event: LocationListEvent) {
        when(event) {
            LocationListEvent.LoadMore -> handleLoadMoreEvent()
        }
    }

    private fun handleLoadMoreEvent() {
        _currentPage.value += 1
    }

}