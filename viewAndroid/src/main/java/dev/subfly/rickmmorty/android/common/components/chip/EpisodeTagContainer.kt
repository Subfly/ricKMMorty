package dev.subfly.rickmmorty.android.common.components.chip

import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString

@Composable
fun EpisodeTagContainer(
    episode: String,
    onClick: () -> Unit = {}
) {

    val textToShow = buildAnnotatedString {
        val partDivisionIndex = episode.indexOf("E")
        val firstPart = episode.substring(
            startIndex = 0,
            endIndex = partDivisionIndex
        )
        val secondPart = episode.subSequence(
            startIndex = partDivisionIndex,
            endIndex = episode.length
        )
        append(
            "$firstPart / $secondPart"
        )
    }

    ElevatedAssistChip(
        onClick = onClick,
        label = {
            Text(
                text = textToShow,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )

}