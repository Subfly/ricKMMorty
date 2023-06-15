package dev.subfly.rickmmorty.android.common.components.item

import androidx.compose.foundation.clickable
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.subfly.rickmmorty.common.models.LocationModel

@Composable
fun LocationListItem(
    model: LocationModel,
    onClickChip: (String) -> Unit = {},
    onClick: () -> Unit = {}
) {
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
                text = "Dimension: ${model.dimension}",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingContent = {
            PlanetTypeContainer(
                type = model.type,
                onClick = {
                    val quotes = listOf(
                        "Eat My Ass, Jerry! He Turned Himself Into Akira!",
                        "I Turned Myself Into A Pickle, Morty!",
                        "I Want That McNugget Sauce, Morty!",
                        "Morty, Sit Here. Summer, You Sit Here. Now, Listen — I Know The Two Of You Are Very Different From Each Other In A Lot Of Ways, But You Have To Understand That As Far As Grandpa's Concerned, You're Both Pieces Of S**t!",
                        "It's Just Rick And Morty! Rick And Morty And Their Adventures, Morty! Rick And Morty Forever And Forever, 100 Years, Rick And Morty's Things!",
                        "Welcome To The Club, Pal.",
                        "Get Out Of Here, Summer! You Ruined The Season 4 Premiere!",
                        "I'm Pickle Rick!",
                        "This One Will Not Be Directed By Ron Howard.",
                        "Wubba Lubba Dub Dub!"
                    )
                    onClickChip(
                        quotes.random()
                    )
                }
            )
        }
    )
}

@Composable
private fun PlanetTypeContainer(
    type: String,
    onClick: () -> Unit = {}
) {
    ElevatedAssistChip(
        onClick = onClick,
        label = {
            Text(
                text = type,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}