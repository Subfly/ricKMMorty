package dev.subfly.rickmmorty.android.view.container.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.android.common.theme.blue.BluePortalLightColors
import dev.subfly.rickmmorty.android.common.theme.green.GreenPortalLightColors
import dev.subfly.rickmmorty.android.common.theme.yellow.YellowPortalLightColors
import dev.subfly.rickmmorty.common.enums.PortalTheme
import dev.subfly.rickmmorty.common.enums.ThemeConfiguration
import dev.subfly.rickmmorty.common.enums.toDisplayText
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions

@Composable
fun ThemeOptionsSheetContent(
    selectedThemeConfiguration: ThemeConfiguration,
    onThemeConfigurationSelected: (ThemeConfiguration) -> Unit,
    selectedPortalTheme: PortalTheme,
    onPortalThemeSelected: (PortalTheme) -> Unit
) {
    val themeConfigurations = ThemeConfiguration.values()
    val portals = PortalTheme.values()
    LazyColumn(
        modifier = Modifier.fillMaxHeight(0.8F)
    ) {
        item {
            Text(
                text = "Theme",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
            )
        }
        items(themeConfigurations) { configuration ->
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(
                            id = when(configuration) {
                                ThemeConfiguration.SYSTEM -> R.drawable.ic_system_mode
                                ThemeConfiguration.LIGHT -> R.drawable.ic_light_mode
                                ThemeConfiguration.DARK -> R.drawable.ic_dark_mode
                            }
                        ),
                        contentDescription = configuration.toDisplayText()
                    )
                },
                headlineContent = {
                    Text(
                        text = configuration.toDisplayText(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    RadioButton(
                        selected = selectedThemeConfiguration == configuration,
                        onClick = {
                            onThemeConfigurationSelected(configuration)
                        }
                    )
                }
            )
        }
        item {
            Text(
                text = "Colors",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp,
                        horizontal = 16.dp
                    )
            )
        }
        items(portals) { portal ->
            ListItem(
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(
                                when(portal) {
                                    PortalTheme.BLUE -> BluePortalLightColors.primary
                                    PortalTheme.GREEN -> GreenPortalLightColors.primary
                                    PortalTheme.YELLOW -> YellowPortalLightColors.primary
                                }
                            )
                    )
                },
                headlineContent = {
                    Text(
                        text = portal.toDisplayText(),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                trailingContent = {
                    RadioButton(
                        selected = selectedPortalTheme == portal,
                        onClick = {
                            onPortalThemeSelected(portal)
                        }
                    )
                }
            )
        }
    }
}