package dev.subfly.rickmmorty.android.view.container.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.search.SearchEvent
import dev.subfly.rickmmorty.state.search.SearchStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class SearchedContentViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: SearchStateMachine by inject {
        parametersOf(viewModelScope)
    }

    val uiState = _stateMachine.state
    val filterState = _stateMachine.filterState
    val queryState = _stateMachine.queryState

    fun onEvent(event: SearchEvent) {
        _stateMachine.onEvent(event)
    }

}