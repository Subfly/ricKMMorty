package dev.subfly.rickmmorty.android.view.container.home.tabs.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.content.list.characterList.CharacterListEvent
import dev.subfly.rickmmorty.state.content.list.characterList.CharacterListStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class CharacterTabViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: CharacterListStateMachine by inject {
        parametersOf(viewModelScope)
    }
    val state = _stateMachine.currentState

    fun onEvent(event: CharacterListEvent) {
        _stateMachine.onEvent(event)
    }

}