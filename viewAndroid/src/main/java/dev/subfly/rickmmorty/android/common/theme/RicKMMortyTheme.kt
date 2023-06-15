package dev.subfly.rickmmorty.android.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.subfly.rickmmorty.android.app.LocalThemeManager
import dev.subfly.rickmmorty.android.common.theme.blue.BluePortalDarkColors
import dev.subfly.rickmmorty.android.common.theme.blue.BluePortalLightColors
import dev.subfly.rickmmorty.android.common.theme.green.GreenPortalDarkColors
import dev.subfly.rickmmorty.android.common.theme.green.GreenPortalLightColors
import dev.subfly.rickmmorty.android.common.theme.yellow.YellowPortalDarkColors
import dev.subfly.rickmmorty.android.common.theme.yellow.YellowPortalLightColors
import dev.subfly.rickmmorty.common.enums.PortalTheme
import dev.subfly.rickmmorty.common.enums.ThemeConfiguration

@Composable
fun RicKMMortyTheme(
    content: @Composable () -> Unit
) {

    val themeManager = LocalThemeManager.current
    val themeState by themeManager.state.collectAsStateWithLifecycle()

    val systemUIController = rememberSystemUiController()
    systemUIController.setSystemBarsColor(
        color = Color.Transparent,
        darkIcons = when(themeState.selectedThemeConfiguration) {
            ThemeConfiguration.SYSTEM -> isSystemInDarkTheme().not()
            ThemeConfiguration.LIGHT -> true
            ThemeConfiguration.DARK -> false
        }
    )

    val colors = when(themeState.selectedThemeConfiguration) {
        ThemeConfiguration.SYSTEM -> {
            when(themeState.selectedPortalTheme) {
                PortalTheme.BLUE -> if (isSystemInDarkTheme()) {
                    BluePortalDarkColors
                } else {
                    BluePortalLightColors
                }
                PortalTheme.GREEN -> if (isSystemInDarkTheme()) {
                    GreenPortalDarkColors
                } else {
                    GreenPortalLightColors
                }
                PortalTheme.YELLOW -> if (isSystemInDarkTheme()) {
                    YellowPortalDarkColors
                } else {
                    YellowPortalLightColors
                }
            }
        }
        ThemeConfiguration.LIGHT -> {
            when(themeState.selectedPortalTheme) {
                PortalTheme.BLUE -> BluePortalLightColors
                PortalTheme.GREEN -> GreenPortalLightColors
                PortalTheme.YELLOW -> YellowPortalLightColors
            }
        }
        ThemeConfiguration.DARK -> {
            when(themeState.selectedPortalTheme) {
                PortalTheme.BLUE -> BluePortalDarkColors
                PortalTheme.GREEN -> GreenPortalDarkColors
                PortalTheme.YELLOW -> YellowPortalDarkColors
            }
        }
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )

}
