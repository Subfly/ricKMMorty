package dev.subfly.rickmmorty.android.view.detail.episode.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.android.common.components.chip.EpisodeTagContainer

@Composable
fun EpisodeDetailMoreInfoContainer(
    episode: String,
    airDate: String
) {
    Column {
        if (episode.isNotEmpty()) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_episode),
                        contentDescription = "Episode Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Episode",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
                    EpisodeTagContainer(
                        episode = episode
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
        }
        if (airDate.isNotEmpty()) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Air Date",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
                    Text(
                        text = airDate,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
        }
    }
}