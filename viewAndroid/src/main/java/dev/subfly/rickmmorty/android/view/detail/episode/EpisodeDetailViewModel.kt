package dev.subfly.rickmmorty.android.view.detail.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.content.detail.episode.EpisodeEvent
import dev.subfly.rickmmorty.state.content.detail.episode.EpisodeStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class EpisodeDetailViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: EpisodeStateMachine by inject {
        parametersOf(viewModelScope)
    }
    val state = _stateMachine.currentState

    fun onEvent(event: EpisodeEvent) {
        _stateMachine.onEvent(event)
    }

}