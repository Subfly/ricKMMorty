package dev.subfly.rickmmorty.state.theme

import dev.subfly.rickmmorty.common.enums.PortalTheme
import dev.subfly.rickmmorty.common.enums.ThemeConfiguration

sealed class ThemeEvent {
    data class SetPortal(val newPortalTheme: PortalTheme): ThemeEvent()
    data class SetThemeConfiguration(val newConfiguration: ThemeConfiguration): ThemeEvent()
}