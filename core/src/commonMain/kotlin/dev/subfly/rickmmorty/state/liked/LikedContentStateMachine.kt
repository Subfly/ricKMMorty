package dev.subfly.rickmmorty.state.liked

import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.domain.useCases.local.WubbaLubbaDubDub
import dev.subfly.rickmmorty.domain.useCases.local.character.GetLikedCharacters
import dev.subfly.rickmmorty.domain.useCases.local.episode.GetLikedEpisodes
import dev.subfly.rickmmorty.domain.useCases.local.location.GetLikedLocations
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class LikedContentStateMachine(
    scope: CoroutineScope? = null
) : BaseStateMachine<LikedContentEvent>(coroutineScope = scope) {

    private val getLikedCharacters: GetLikedCharacters by inject {
        parametersOf(mainDispatcher)
    }
    private val getLikedEpisodes: GetLikedEpisodes by inject {
        parametersOf(mainDispatcher)
    }
    private val getLikedLocations: GetLikedLocations by inject {
        parametersOf(mainDispatcher)
    }

    private val wubbaLubbaDubDubUseCase: WubbaLubbaDubDub by inject()

    private val _state = MutableStateFlow(LikedContentState())
    val currentState = _state.toCommonStateFlow()

    init {
        stateMachineScope.launch {
            combine(
                getLikedCharacters(),
                getLikedEpisodes(),
                getLikedLocations()
            ) { charactersResponse, episodesResponse, locationsResponse ->
                _state.update {
                    it.copy(
                        isLoading = charactersResponse is Resource.Loading
                                || episodesResponse is Resource.Loading
                                || locationsResponse is Resource.Loading,
                        likedCharacters = if (charactersResponse is Resource.Success) {
                            charactersResponse.data
                        } else {
                            it.likedCharacters
                        },
                        likedCharacterIds = if (charactersResponse is Resource.Success) {
                            charactersResponse.data.map { character -> character.id }.toList()
                        } else {
                            it.likedCharacterIds
                        },
                        likedEpisodes = if (episodesResponse is Resource.Success) {
                            episodesResponse.data
                        } else {
                            it.likedEpisodes
                        },
                        likedEpisodeIds = if (episodesResponse is Resource.Success) {
                            episodesResponse.data.map { episode -> episode.id }.toList()
                        } else {
                            it.likedEpisodeIds
                        },
                        likedLocations = if (locationsResponse is Resource.Success) {
                            locationsResponse.data
                        } else {
                            it.likedLocations
                        },
                        likedLocationIds = if (locationsResponse is Resource.Success) {
                            locationsResponse.data.map { location -> location.id }.toList()
                        } else {
                            it.likedLocationIds
                        },
                    )
                }
            }.distinctUntilChanged().launchIn(this)
        }
    }

    override fun onEvent(event: LikedContentEvent) {
        when(event) {
            LikedContentEvent.WubbaLubbaDubDub -> wubbaLubbaDubDub()
        }
    }

    private fun wubbaLubbaDubDub() {
        stateMachineScope.launch {
            wubbaLubbaDubDubUseCase()
        }
    }

}