package dev.subfly.rickmmorty.common.extension

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import dev.subfly.rickmmorty.common.enums.HomeTab

fun HomeTab.getIcon(): ImageVector {
    return when(this) {
        HomeTab.CHARACTERS -> Icons.Outlined.Person
        HomeTab.EPISODES -> Icons.Outlined.PlayArrow
        HomeTab.LOCATIONS -> Icons.Outlined.Place
    }
}
