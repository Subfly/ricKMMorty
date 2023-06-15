package dev.subfly.rickmmorty.android.view.detail.location.components

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

@Composable
fun LocationDetailMoreInfoContainer(
    dimension: String,
    type: String
) {
    Column {
        if (dimension.isNotEmpty()) {
            ListItem(
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dimension),
                        contentDescription = "Calendar Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Dimension",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
                    Text(
                        text = dimension,
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
                        contentDescription = "Tag Icon",
                        modifier = Modifier.size(24.dp)
                    )
                },
                headlineContent = {
                    Text(
                        text = "Type",
                        style = MaterialTheme.typography.bodyMedium
                    )
                },
                trailingContent = {
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