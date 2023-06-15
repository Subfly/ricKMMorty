package dev.subfly.rickmmorty.android.view.container.liked

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.subfly.rickmmorty.android.R
import dev.subfly.rickmmorty.android.app.LocalLikedContentProvider
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.components.item.CharacterListItem
import dev.subfly.rickmmorty.android.common.components.item.EpisodeListItem
import dev.subfly.rickmmorty.android.common.components.item.LocationListItem
import dev.subfly.rickmmorty.android.common.components.loading.RicKMMortyLoadingIndicator
import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.android.view.container.liked.components.LikedContentTitle
import dev.subfly.rickmmorty.android.view.container.liked.components.LikedContentTopBar
import dev.subfly.rickmmorty.android.view.container.liked.components.NoLikedContentView
import dev.subfly.rickmmorty.android.view.container.liked.components.WubbaLubbaDubDubDialog
import dev.subfly.rickmmorty.state.liked.LikedContentEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LikedContentContainer(
    viewModel: LikedContentViewModel = koinViewModel()
) {

    val likedContentProvider = LocalLikedContentProvider.current
    val navController = LocalNavigationController.current

    val likedContentState by likedContentProvider.state.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()

    val appliedFilters = filterState.appliedFilters

    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        WubbaLubbaDubDubDialog(
            onDismiss = {
                showDialog = false
            },
            onCancel = {
                showDialog = false
            },
            onConfirm = {
                likedContentProvider.onEvent(
                    event = LikedContentEvent.WubbaLubbaDubDub
                )
                showDialog = false
            },
        )
    }

    Scaffold(
        topBar = {
            LikedContentTopBar(
                appliedFilters = appliedFilters,
                onClickWubbaLubbaDubDub = {
                    showDialog = true
                },
                onClickFilter = { filterEvent ->
                    viewModel.onEvent(
                        event = filterEvent
                    )
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            if (likedContentState.isLoading) {
                RicKMMortyLoadingIndicator(
                    modifier = Modifier.align(
                        Alignment.TopCenter
                    )
                )
            }

            if (
                likedContentState.likedCharacters.isEmpty()
                && likedContentState.likedEpisodes.isEmpty()
                && likedContentState.likedLocations.isEmpty()
            ) {
                NoLikedContentView()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {

                    val shouldShowCharacterList = viewModel.shouldShowContent(
                        items = likedContentState.likedCharacters,
                        filters = appliedFilters,
                        filter = ContentFilter.CHARACTER
                    )

                    val shouldShowEpisodeList = viewModel.shouldShowContent(
                        items = likedContentState.likedEpisodes,
                        filters = appliedFilters,
                        filter = ContentFilter.EPISODE
                    )

                    val shouldShowLocationList = viewModel.shouldShowContent(
                        items = likedContentState.likedLocations,
                        filters = appliedFilters,
                        filter = ContentFilter.LOCATION
                    )

                    if (shouldShowCharacterList) {
                        stickyHeader {
                            LikedContentTitle(
                                title = "Characters",
                                icon = R.drawable.ic_character
                            )
                        }
                        items(likedContentState.likedCharacters) { character ->
                            CharacterListItem(
                                model = character,
                                onClick = {
                                    navController.navigate(
                                        Detail.Character.passArguments(
                                            characterId = character.id,
                                            characterName = character.name
                                        )
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                        }
                    }

                    if (shouldShowEpisodeList) {
                        stickyHeader {
                            LikedContentTitle(
                                title = "Episodes",
                                icon = R.drawable.ic_episode
                            )
                        }
                        items(likedContentState.likedEpisodes) { episode ->
                            EpisodeListItem(
                                model = episode,
                                onClick = {
                                    navController.navigate(
                                        Detail.Episode.passArguments(
                                            episodeId = episode.id,
                                            episodeTitle = episode.name
                                        )
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                        }
                    }

                    if (shouldShowLocationList) {
                        stickyHeader {
                            LikedContentTitle(
                                title = "Locations",
                                icon = R.drawable.ic_location
                            )
                        }
                        items(likedContentState.likedLocations) { location ->
                            LocationListItem(
                                model = location,
                                onClick = {
                                    navController.navigate(
                                        Detail.Location.passArguments(
                                            locationId = location.id,
                                            locationName = location.name
                                        )
                                    )
                                }
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                        }
                    }

                }
            }

        }
    }
}
