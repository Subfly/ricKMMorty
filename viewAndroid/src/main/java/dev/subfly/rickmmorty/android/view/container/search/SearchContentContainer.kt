package dev.subfly.rickmmorty.android.view.container.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.subfly.rickmmorty.android.app.LocalNavigationController
import dev.subfly.rickmmorty.android.common.enums.SearchContentBottomSheetContent
import dev.subfly.rickmmorty.android.navigation.destinations.Detail
import dev.subfly.rickmmorty.android.view.container.search.components.ChangeFilterOptionsSheetContent
import dev.subfly.rickmmorty.android.view.container.search.components.ChangeFilterSheetContent
import dev.subfly.rickmmorty.android.view.container.search.components.EmptyQueryView
import dev.subfly.rickmmorty.android.view.container.search.components.SearchTopBar
import dev.subfly.rickmmorty.android.view.container.search.components.SearchedCharactersComponent
import dev.subfly.rickmmorty.android.view.container.search.components.SearchedEpisodesComponent
import dev.subfly.rickmmorty.android.view.container.search.components.SearchedLocationsComponent
import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.state.search.SearchEvent
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchContentContainer(
    viewModel: SearchedContentViewModel = koinViewModel()
) {

    val navController = LocalNavigationController.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val queryState by viewModel.queryState.collectAsStateWithLifecycle()

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var shouldShowModal by remember {
        mutableStateOf(false)
    }

    var currentSheetType by remember {
        mutableStateOf(SearchContentBottomSheetContent.CHANGE_FILTER)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            topBar = {
                SearchTopBar(
                    query = queryState,
                    currentFilter = filterState.appliedFilter,
                    onQueryChanged = { newQuery ->
                        viewModel.onEvent(
                            event = SearchEvent.OnQueryChanged(
                                newQuery = newQuery
                            )
                        )
                    },
                    onDeleteQuery = {
                        viewModel.onEvent(
                            event = SearchEvent.OnQueryChanged(
                                newQuery = ""
                            )
                        )
                    },
                    onClickChangeFilter = {
                        currentSheetType = SearchContentBottomSheetContent.CHANGE_FILTER
                        shouldShowModal = true
                    },
                    onClickChangeFilterOptions = {
                        currentSheetType = SearchContentBottomSheetContent.CHANGE_FILTER_OPTIONS
                        shouldShowModal = true
                    }
                )
            }
        ) { paddings ->
            if (queryState.isEmpty()) {
                EmptyQueryView(
                    appliedFilter = filterState.appliedFilter
                )
            } else {
                when (filterState.appliedFilter) {
                    ContentFilter.CHARACTER -> {
                        SearchedCharactersComponent(
                            modifier = Modifier.padding(paddings),
                            isLoading = uiState.isLoading,
                            errorMessage = uiState.error,
                            charactersList = uiState.searchedCharacters,
                            onLoadMore = {
                                viewModel.onEvent(
                                    event = SearchEvent.LoadMore
                                )
                            },
                            onClickItem = { model ->
                                navController.navigate(
                                    Detail.Character.passArguments(
                                        characterId = model.id,
                                        characterName = model.name
                                    )
                                )
                            }
                        )
                    }

                    ContentFilter.EPISODE -> {
                        SearchedEpisodesComponent(
                            modifier = Modifier.padding(paddings),
                            isLoading = uiState.isLoading,
                            errorMessage = uiState.error,
                            episodesList = uiState.searchedEpisodes,
                            onLoadMore = {
                                viewModel.onEvent(
                                    event = SearchEvent.LoadMore
                                )
                            },
                            onClickItem = { model ->
                                navController.navigate(
                                    Detail.Episode.passArguments(
                                        episodeId = model.id,
                                        episodeTitle = model.name
                                    )
                                )
                            }
                        )
                    }

                    ContentFilter.LOCATION -> {
                        SearchedLocationsComponent(
                            modifier = Modifier.padding(paddings),
                            isLoading = uiState.isLoading,
                            errorMessage = uiState.error,
                            locationsList = uiState.searchedLocations,
                            onLoadMore = {
                                viewModel.onEvent(
                                    event = SearchEvent.LoadMore
                                )
                            },
                            onClickItem = { model ->
                                navController.navigate(
                                    Detail.Location.passArguments(
                                        locationId = model.id,
                                        locationName = model.name
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
        if (shouldShowModal) {
            ModalBottomSheet(
                onDismissRequest = {
                    currentSheetType = SearchContentBottomSheetContent.NONE
                    shouldShowModal = false
                },
                sheetState = bottomSheetState
            ) {
                when (currentSheetType) {

                    SearchContentBottomSheetContent.CHANGE_FILTER -> {
                        ChangeFilterSheetContent(
                            currentFilter = filterState.appliedFilter,
                            onSelectFilter = { newFilter ->
                                viewModel.onEvent(
                                    event = SearchEvent.OnFilterChanged(
                                        newFilter = newFilter
                                    )
                                )
                            }
                        )
                    }

                    SearchContentBottomSheetContent.CHANGE_FILTER_OPTIONS -> {
                        ChangeFilterOptionsSheetContent(
                            appliedFilter = filterState.appliedFilter,
                            characterOptions = filterState.characterFilterOptions,
                            episodeOptions = filterState.episodeFilterOptions,
                            locationOptions = filterState.locationFilterOptions,
                            onCharacterOptionsChanged = { newOptions ->
                                viewModel.onEvent(
                                    event = SearchEvent.OnCharacterFilterOptionsChanged(
                                        newOptions = newOptions
                                    )
                                )

                            },
                            onEpisodeOptionChanged = { newOptions ->
                                viewModel.onEvent(
                                    event = SearchEvent.OnEpisodeFilterOptionsChanged(
                                        newOptions = newOptions
                                    )
                                )
                            },
                            onLocationOptionChanged = { newOptions ->
                                viewModel.onEvent(
                                    event = SearchEvent.OnLocationFilterOptionsChanged(
                                        newOptions = newOptions
                                    )
                                )
                            }
                        )
                    }

                    SearchContentBottomSheetContent.NONE -> {
                        Box(modifier = Modifier.size(1.dp))
                    }

                }
            }
        }
    }
}
