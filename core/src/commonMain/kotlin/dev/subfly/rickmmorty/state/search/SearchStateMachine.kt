package dev.subfly.rickmmorty.state.search

import dev.subfly.rickmmorty.common.enums.ContentFilter
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.domain.useCases.remote.search.SearchCharacter
import dev.subfly.rickmmorty.domain.useCases.remote.search.SearchEpisode
import dev.subfly.rickmmorty.domain.useCases.remote.search.SearchLocation
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

@OptIn(FlowPreview::class)
class SearchStateMachine(
    scope: CoroutineScope? = null
) : BaseStateMachine<SearchEvent>(coroutineScope = scope) {

    private val _paginationPageState = MutableStateFlow(1)

    private val searchCharacterUseCase: SearchCharacter by inject()
    private val searchEpisodeUseCase: SearchEpisode by inject()
    private val searchLocationUseCase: SearchLocation by inject()

    private val _state = MutableStateFlow(SearchState())
    val state = _state.toCommonStateFlow()

    private val _filterState = MutableStateFlow(SearchFilterState())
    val filterState = _filterState.toCommonStateFlow()

    private val _queryState = MutableStateFlow("")
    val queryState = _queryState.toCommonStateFlow()

    init {
        combine(
            _filterState,
            _queryState.debounce(250),
            _paginationPageState
        ) { currentFilter, currentQuery, currentPage ->
            if (currentQuery.isEmpty()) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = "",
                        searchedCharacters = emptyList(),
                        searchedEpisodes = emptyList(),
                        searchedLocations = emptyList()
                    )
                }
            } else {
                when (currentFilter.appliedFilter) {
                    ContentFilter.CHARACTER -> {
                        searchCharacterUseCase(
                            page = currentPage,
                            query = currentQuery,
                            options = currentFilter.characterFilterOptions
                        ).collectLatest { resource ->
                            _state.update {
                                it.copy(
                                    isLoading = resource is Resource.Loading,
                                    error = if (resource is Resource.Error) {
                                        resource.message
                                    } else {
                                        ""
                                    },
                                    searchedCharacters = if (resource is Resource.Success) {
                                        val currentCharacters = it.searchedCharacters.toMutableList()
                                        currentCharacters.addAll(resource.data)
                                        currentCharacters
                                    } else {
                                        it.searchedCharacters
                                    },
                                    searchedEpisodes = emptyList(),
                                    searchedLocations = emptyList()
                                )
                            }
                        }
                    }

                    ContentFilter.EPISODE -> {
                        searchEpisodeUseCase(
                            page = currentPage,
                            query = currentQuery,
                            options = currentFilter.episodeFilterOptions
                        ).collectLatest { resource ->
                            _state.update {
                                it.copy(
                                    isLoading = resource is Resource.Loading,
                                    error = if (resource is Resource.Error) {
                                        resource.message
                                    } else {
                                        ""
                                    },
                                    searchedCharacters = emptyList(),
                                    searchedEpisodes = if (resource is Resource.Success) {
                                        val currentEpisodes = it.searchedEpisodes.toMutableList()
                                        currentEpisodes.addAll(resource.data)
                                        currentEpisodes
                                    } else {
                                        it.searchedEpisodes
                                    },
                                    searchedLocations = emptyList()
                                )
                            }
                        }
                    }

                    ContentFilter.LOCATION -> {
                        searchLocationUseCase(
                            page = currentPage,
                            query = currentQuery,
                            options = currentFilter.locationFilterOptions
                        ).collectLatest { resource ->
                            _state.update {
                                it.copy(
                                    isLoading = resource is Resource.Loading,
                                    error = if (resource is Resource.Error) {
                                        resource.message
                                    } else {
                                        ""
                                    },
                                    searchedCharacters = emptyList(),
                                    searchedEpisodes = emptyList(),
                                    searchedLocations = if (resource is Resource.Success) {
                                        val currentLocations = it.searchedLocations.toMutableList()
                                        currentLocations.addAll(resource.data)
                                        currentLocations
                                    } else {
                                        it.searchedLocations
                                    },
                                )
                            }
                        }
                    }
                }
            }
        }.launchIn(stateMachineScope)
    }

    override fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnFilterChanged -> handleFilterChangeEvent(event)

            is SearchEvent.OnCharacterFilterOptionsChanged -> {
                handleCharacterFilterOptionsChangeEvent(event)
            }

            is SearchEvent.OnEpisodeFilterOptionsChanged -> {
                handleEpisodeFilterOptionsChangeEvent(event)
            }

            is SearchEvent.OnLocationFilterOptionsChanged -> {
                handleLocationFilterOptionsChangeEvent(event)
            }

            is SearchEvent.OnQueryChanged -> handleQueryChangeEvent(event)

            SearchEvent.LoadMore -> handleLoadMoreEvent()
        }
    }

    private fun handleFilterChangeEvent(event: SearchEvent.OnFilterChanged) {
        _filterState.update {
            it.copy(
                appliedFilter = event.newFilter
            )
        }
        _queryState.update { "" }
        _paginationPageState.update { 1 }
    }

    private fun handleCharacterFilterOptionsChangeEvent(
        event: SearchEvent.OnCharacterFilterOptionsChanged
    ) {
        _filterState.update {
            it.copy(
                characterFilterOptions = event.newOptions
            )
        }
        _paginationPageState.update { 1 }
    }

    private fun handleEpisodeFilterOptionsChangeEvent(
        event: SearchEvent.OnEpisodeFilterOptionsChanged
    ) {
        _filterState.update {
            it.copy(
                episodeFilterOptions = event.newOptions
            )
        }
        _paginationPageState.update { 1 }
    }

    private fun handleLocationFilterOptionsChangeEvent(
        event: SearchEvent.OnLocationFilterOptionsChanged
    ) {
        _filterState.update {
            it.copy(
                locationFilterOptions = event.newOptions
            )
        }
        _paginationPageState.update { 1 }
    }

    private fun handleQueryChangeEvent(event: SearchEvent.OnQueryChanged) {
        _queryState.update { _ ->
            event.newQuery
        }
        _paginationPageState.update { 1 }
    }

    private fun handleLoadMoreEvent() {
        _paginationPageState.update {
            it + 1
        }
    }
}