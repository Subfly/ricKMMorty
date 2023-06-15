package dev.subfly.rickmmorty.state.base

import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

abstract class BaseStateMachine<E>(
    private val coroutineScope: CoroutineScope? = null
) : KoinComponent {

    // Scope Definition
    val stateMachineScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)
    val mainDispatcher = Dispatchers.Main + SupervisorJob()

    // Event Handler
    abstract fun onEvent(event: E)

    companion object {
        const val INVALID_ID = -1
    }

}