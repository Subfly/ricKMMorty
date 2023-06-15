package dev.subfly.rickmmorty.android.navigation.destinations

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dev.subfly.rickmmorty.android.common.constants.NavConstants
import dev.subfly.rickmmorty.android.common.enums.NavParams
import dev.subfly.rickmmorty.android.navigation.base.BaseDestination

sealed class Detail {

    object Character : BaseDestination() {
        override val route: String
            get() = createRoute(
                route = "character",
                parameters = listOf(
                    NavParams.ID,
                    NavParams.TITLE
                )
            )
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(name = NavParams.ID.name) {
                    type = NavType.IntType
                    defaultValue = NavConstants.DEFAULT_INT_ARG_VALUE
                },
                navArgument(name = NavParams.TITLE.name) {
                    type = NavType.StringType
                    defaultValue = NavConstants.DEFAULT_STRING_ARG_VALUE
                }
            )
        override val screenRoute: String
            get() = "character_route"

        fun passArguments(
            characterId: Int,
            characterName: String
        ) = createRouteWithParams(
            route = "character",
            parameters = mapOf(
                NavParams.ID to characterId,
                NavParams.TITLE to characterName
            )
        )

    }

    object Episode : BaseDestination() {
        override val route: String
            get() = createRoute(
                route = "episode",
                parameters = listOf(
                    NavParams.ID,
                    NavParams.TITLE
                )
            )
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(name = NavParams.ID.name) {
                    type = NavType.IntType
                    defaultValue = NavConstants.DEFAULT_INT_ARG_VALUE
                },
                navArgument(name = NavParams.TITLE.name) {
                    type = NavType.StringType
                    defaultValue = NavConstants.DEFAULT_STRING_ARG_VALUE
                }
            )
        override val screenRoute: String
            get() = "episode_route"

        fun passArguments(
            episodeId: Int,
            episodeTitle: String
        ) = createRouteWithParams(
            route = "episode",
            parameters = mapOf(
                NavParams.ID to episodeId,
                NavParams.TITLE to episodeTitle
            )
        )

    }

    object Location : BaseDestination() {
        override val route: String
            get() = createRoute(
                route = "location",
                parameters = listOf(
                    NavParams.ID,
                    NavParams.TITLE
                )
            )
        override val arguments: List<NamedNavArgument>
            get() = listOf(
                navArgument(name = NavParams.ID.name) {
                    type = NavType.IntType
                    defaultValue = NavConstants.DEFAULT_INT_ARG_VALUE
                },
                navArgument(name = NavParams.TITLE.name) {
                    type = NavType.StringType
                    defaultValue = NavConstants.DEFAULT_STRING_ARG_VALUE
                }
            )
        override val screenRoute: String
            get() = "location_route"

        fun passArguments(
            locationId: Int,
            locationName: String
        ) = createRouteWithParams(
            route = "location",
            parameters = mapOf(
                NavParams.ID to locationId,
                NavParams.TITLE to locationName
            )
        )

    }

}