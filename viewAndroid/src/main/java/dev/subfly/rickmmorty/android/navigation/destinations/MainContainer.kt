package dev.subfly.rickmmorty.android.navigation.destinations

import androidx.navigation.NamedNavArgument
import dev.subfly.rickmmorty.android.navigation.base.BaseDestination

object MainContainer : BaseDestination() {
    override val route: String
        get() = "main_container"
    override val arguments: List<NamedNavArgument>
        get() = emptyList()
    override val screenRoute: String
        get() = "main_container_route"
}