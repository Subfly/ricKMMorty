package dev.subfly.rickmmorty.android.view.detail.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.content.detail.character.CharacterEvent
import dev.subfly.rickmmorty.state.content.detail.character.CharacterStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class CharacterDetailViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: CharacterStateMachine by inject {
        parametersOf(viewModelScope)
    }
    val state = _stateMachine.currentState

    fun onEvent(event: CharacterEvent) {
        _stateMachine.onEvent(event)
    }

}