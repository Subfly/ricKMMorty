package dev.subfly.rickmmorty.android.view.container.home.tabs.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.content.list.episodeList.EpisodeListEvent
import dev.subfly.rickmmorty.state.content.list.episodeList.EpisodeListStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class EpisodeTabViewModel : ViewModel(), KoinComponent {

    private val _stateMachine: EpisodeListStateMachine by inject {
        parametersOf(viewModelScope)
    }
    val state = _stateMachine.currentState

    fun onEvent(event: EpisodeListEvent) {
        _stateMachine.onEvent(event)
    }

}