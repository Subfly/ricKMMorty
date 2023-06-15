package dev.subfly.rickmmorty.android.view.detail.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.content.detail.location.LocationEvent
import dev.subfly.rickmmorty.state.content.detail.location.LocationStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class LocationDetailViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: LocationStateMachine by inject {
        parametersOf(viewModelScope)
    }
    val state = _stateMachine.currentState

    fun onEvent(event: LocationEvent) {
        _stateMachine.onEvent(event)
    }

}