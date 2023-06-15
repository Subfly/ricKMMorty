package dev.subfly.rickmmorty.android.common.components.item

import androidx.compose.foundation.clickable
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import dev.subfly.rickmmorty.android.common.components.chip.EpisodeTagContainer
import dev.subfly.rickmmorty.common.models.EpisodeModel

private const val EASTER_EGG_MAX_TAP_COUNT = 7

@Composable
fun EpisodeListItem(
    model: EpisodeModel,
    onClick: () -> Unit = {},
    onClickChip: (String) -> Unit = {}
) {

    var showEasterEgg by remember {
        mutableIntStateOf(0)
    }

    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        headlineContent = {
            Text(
                text = model.name,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingContent = {
            Text(
                text = model.airDate,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingContent = {
            EpisodeTagContainer(
                episode = model.episode,
                onClick = {
                    showEasterEgg += 1
                    if (showEasterEgg == EASTER_EGG_MAX_TAP_COUNT) {
                        showEasterEgg = 0
                        onClickChip("You are now a developer!")
                    } else {
                        val remainingSteps = EASTER_EGG_MAX_TAP_COUNT - showEasterEgg
                        val textToFire = buildString {
                            append("You are now $remainingSteps step")
                            if (remainingSteps > 1) {
                                append("s ")
                            } else {
                                append(" ")
                            }
                            append("away from being a developer")
                        }
                        onClickChip(textToFire)
                    }
                }
            )
        }
    )

}