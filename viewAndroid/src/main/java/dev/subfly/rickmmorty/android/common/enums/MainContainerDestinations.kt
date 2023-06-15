package dev.subfly.rickmmorty.android.common.enums

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class MainContainerDestinations(
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    HOME(
        title = "Home",
        icon = Icons.Outlined.Home,
        route = "home_route"
    ),

    SEARCH(
        title = "Search",
        icon = Icons.Outlined.Search,
        route = "search_route"
    ),

    FAVORITES(
        title = "Favorites",
        icon = Icons.Outlined.FavoriteBorder,
        route = "favorites_route"
    )

}
