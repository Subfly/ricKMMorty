package dev.subfly.rickmmorty.state.theme

import dev.subfly.rickmmorty.common.enums.PortalTheme
import dev.subfly.rickmmorty.common.enums.ThemeConfiguration

data class ThemeState(
    val selectedPortalTheme: PortalTheme = PortalTheme.BLUE,
    val selectedThemeConfiguration: ThemeConfiguration = ThemeConfiguration.SYSTEM
)