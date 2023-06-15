package dev.subfly.rickmmorty.android.common.components.item

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.enums.CharacterStatus
import dev.subfly.rickmmorty.common.enums.toDisplayText

@Composable
fun CharacterListItem(
    model: CharacterModel,
    onClickStatus: (String) -> Unit = {},
    onClick: () -> Unit = {}
) {

    var canImageBeLoaded by remember {
        mutableStateOf(true)
    }

    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        leadingContent = {
            if (canImageBeLoaded) {
                SubcomposeAsyncImage(
                    model = model.imageUrl,
                    contentDescription = "${model.name} Image",
                    contentScale = ContentScale.Crop,
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .size(52.dp)
                        .clip(CircleShape),
                    onError = {
                        canImageBeLoaded = false
                    },
                    loading = {
                        CircularProgressIndicator()
                    }
                )
            }
        },
        headlineContent = {
            Text(
                text = model.name,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        supportingContent = {
            Text(
                text = model.species,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        trailingContent = {
            DeadOrAliveIndicator(
                status = model.status,
                onClick = {
                    onClickStatus(model.status.toDisplayText())
                }
            )
        },
    )
}

@Composable
private fun DeadOrAliveIndicator(
    status: CharacterStatus,
    onClick: () -> Unit
) {

    val infiniteTransitionState = rememberInfiniteTransition(
        label = "Infinite Transition"
    )

    val color by infiniteTransitionState.animateColor(
        initialValue = when (status) {
            CharacterStatus.UNKNOWN,
            CharacterStatus.NONE -> Color(0xFF15616D)
            CharacterStatus.ALIVE -> Color(0xFF4CB944)
            CharacterStatus.DEAD -> Color(0xFFEF476F)
        },
        targetValue = Color.Gray,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Color Transition"
    )

    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(
                id = when(status) {
                    CharacterStatus.UNKNOWN,
                    CharacterStatus.NONE -> R.drawable.ic_unknown
                    CharacterStatus.ALIVE -> R.drawable.ic_alive
                    CharacterStatus.DEAD -> R.drawable.ic_dead
                }
            ),
            contentDescription = status.value,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
    }

}