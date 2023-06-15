package dev.subfly.rickmmorty.state.theme

import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ThemeStateMachine(
    scope: CoroutineScope? = null
) : BaseStateMachine<ThemeEvent>(coroutineScope = scope) {

    private val _state = MutableStateFlow(ThemeState())
    val state = _state.toCommonStateFlow()

    override fun onEvent(event: ThemeEvent) {
        when(event) {
            is ThemeEvent.SetPortal -> handleSetPortalEvent(event)
            is ThemeEvent.SetThemeConfiguration -> handleSetThemeConfigurationEvent(event)
        }
    }

    private fun handleSetPortalEvent(event: ThemeEvent.SetPortal) {
        _state.update {
            it.copy(
                selectedPortalTheme = event.newPortalTheme
            )
        }
    }

    private fun handleSetThemeConfigurationEvent(event: ThemeEvent.SetThemeConfiguration) {
        _state.update {
            it.copy(
                selectedThemeConfiguration = event.newConfiguration
            )
        }
    }

}