package dev.subfly.rickmmorty.android.view.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.subfly.rickmmorty.android.common.enums.MainContainerDestinations
import dev.subfly.rickmmorty.android.view.container.home.HomeContentContainer
import dev.subfly.rickmmorty.android.view.container.liked.LikedContentContainer
import dev.subfly.rickmmorty.android.view.container.search.SearchContentContainer

private const val MAIN_CONTAINER_TAB_ROUTE = "main_container_tab_route"

@Composable
fun MainContainer() {

    val mainContainerNavController = rememberNavController()
    val destinations = listOf(
        MainContainerDestinations.HOME,
        MainContainerDestinations.SEARCH,
        MainContainerDestinations.FAVORITES
    )

    Scaffold(
        bottomBar = {
            val navBackStackEntry by mainContainerNavController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            NavigationBar {
                destinations.forEach { destination ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {
                            it.route == destination.route
                        } == true,
                        onClick = {
                            mainContainerNavController.navigate(destination.route) {
                                popUpTo(mainContainerNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.title
                            )
                        },
                        label = {
                            Text(
                                text = destination.title,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = mainContainerNavController,
            route = MAIN_CONTAINER_TAB_ROUTE,
            startDestination = destinations[0].route,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {

            composable(
                route = MainContainerDestinations.HOME.route
            ) {
                HomeContentContainer()
            }

            composable(
                route = MainContainerDestinations.SEARCH.route
            ) {
                SearchContentContainer()
            }

            composable(
                route = MainContainerDestinations.FAVORITES.route
            ) {
                LikedContentContainer()
            }

        }

    }

}