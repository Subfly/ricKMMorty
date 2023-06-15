package dev.subfly.rickmmorty.android.common.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.subfly.rickmmorty.state.liked.LikedContentEvent
import dev.subfly.rickmmorty.state.liked.LikedContentStateMachine
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class LikedContentProvider : ViewModel(), KoinComponent{

    private val stateMachine: LikedContentStateMachine by inject {
        parametersOf(viewModelScope)
    }

    val state = stateMachine.currentState

    fun onEvent(event: LikedContentEvent) {
        stateMachine.onEvent(event)
    }

}