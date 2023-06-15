package dev.subfly.rickmmorty.android.view.container.home.tabs.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.content.list.locationList.LocationListEvent
import dev.subfly.rickmmorty.state.content.list.locationList.LocationListStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class LocationTabViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: LocationListStateMachine by inject {
        parametersOf(viewModelScope)
    }
    val state = _stateMachine.currentState

    fun onEvent(event: LocationListEvent) {
        _stateMachine.onEvent(event)
    }

}