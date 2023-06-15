package dev.subfly.rickmmorty.android.view.detail.character.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import dev.subfly.rickmmorty.common.enums.CharacterGender
import dev.subfly.rickmmorty.common.enums.CharacterStatus
import dev.subfly.rickmmorty.common.enums.toDisplayText

@Composable
fun CharacterDetailMoreInfoContainer(
    species: String,
    gender: CharacterGender,
    status: CharacterStatus,
    type: String

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (species.isNotEmpty()) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_species),
                        contentDescription = "Species Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Species",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
                    Text(
                        text = species,
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
        }
        if (gender != CharacterGender.NONE) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(
                            id = when(gender) {
                                CharacterGender.NONE,
                                CharacterGender.UNKNOWN -> R.drawable.ic_gender
                                CharacterGender.FEMALE -> R.drawable.ic_female
                                CharacterGender.MALE -> R.drawable.ic_male
                                CharacterGender.GENDERLESS -> R.drawable.ic_genderless
                            }
                        ),
                        contentDescription = "Gender Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Gender",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
                    Text(
                        text = gender.toDisplayText(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
        }
        if (status != CharacterStatus.NONE) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(
                            id = when(status) {
                                CharacterStatus.NONE,
                                CharacterStatus.UNKNOWN -> R.drawable.ic_unknown
                                CharacterStatus.ALIVE -> R.drawable.ic_alive
                                CharacterStatus.DEAD -> R.drawable.ic_dead
                            }
                        ),
                        contentDescription = "Status Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Status",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
                    Text(
                        text = status.toDisplayText(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )
            )
        }
        if (type.isNotEmpty()) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_tag),
                        contentDescription = "Type Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = type,
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