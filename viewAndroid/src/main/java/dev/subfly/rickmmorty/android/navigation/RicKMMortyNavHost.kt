package dev.subfly.rickmmorty.android.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.constants.NavConstants
import dev.subfly.rickmmorty.android.common.enums.NavParams
import dev.subfly.rickmmorty.android.common.extensions.orMinusOne
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.android.navigation.destinations.MainContainer
import dev.subfly.rickmmorty.android.view.container.MainContainer
import dev.subfly.rickmmorty.android.view.detail.character.CharacterDetailScreen
import dev.subfly.rickmmorty.android.view.detail.episode.EpisodeDetailScreen
import dev.subfly.rickmmorty.android.view.detail.location.LocationDetailScreen

@Composable
fun RicKMMortyNavHost() {

    val navigator = LocalNavigationController.current

    NavHost(
        navController = navigator,
        route = NavConstants.ROOT_ROUTE,
        startDestination = MainContainer.route
    ) {

        composable(
            route = MainContainer.route
        ) {
            MainContainer()
        }

        // TODO: FOR SOME REASON, BUNDLE.GET_INT DID NOT WORKED SO PARSED STRING DIRECTLY

        composable(
            route = Detail.Character.route
        ) {

            val characterId = it.arguments?.getString(
                NavParams.ID.argName
            ).orEmpty().toInt()

            val characterName = it.arguments?.getString(
                NavParams.TITLE.argName
            ).orEmpty()

            CharacterDetailScreen(
                id = characterId,
                title = characterName
            )

        }

        composable(
            route = Detail.Episode.route
        ) {

            val episodeId = it.arguments?.getString(
                NavParams.ID.argName
            ).orEmpty().toInt()

            val episodeName = it.arguments?.getString(
                NavParams.TITLE.argName
            ).orEmpty()

            EpisodeDetailScreen(
                id = episodeId,
                title = episodeName
            )

        }

        composable(
            route = Detail.Location.route
        ) {

            val locationId = it.arguments?.getString(
                NavParams.ID.argName
            ).orEmpty().toInt()

            val locationName = it.arguments?.getString(
                NavParams.TITLE.argName
            ).orEmpty()

            LocationDetailScreen(
                id = locationId,
                title = locationName
            )

        }

    }

}