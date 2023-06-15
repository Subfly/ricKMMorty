package dev.subfly.rickmmorty.android.common.managers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.theme.ThemeEvent
import dev.subfly.rickmmorty.state.theme.ThemeStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class ThemeManager : ViewModel(), KoinComponent {

    private val _stateMachine: ThemeStateMachine by inject {
        parametersOf(viewModelScope)
    }

    val state = _stateMachine.state

    fun onEvent(event: ThemeEvent) {
        _stateMachine.onEvent(event)
    }

}