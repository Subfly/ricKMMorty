package dev.subfly.rickmmorty.state.content.detail.episode

import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetEpisodeById
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.domain.useCases.local.episode.DislikeEpisode
import dev.subfly.rickmmorty.domain.useCases.local.episode.LikeEpisode
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class EpisodeStateMachine(
    scope: CoroutineScope? = null
) : BaseStateMachine<EpisodeEvent>(coroutineScope = scope) {

    private val getEpisodeUseCase: GetEpisodeById by inject()
    private val likeEpisodeUseCase: LikeEpisode by inject()
    private val dislikeEpisodeUseCase: DislikeEpisode by inject()

    private val _state = MutableStateFlow(EpisodeState())
    val currentState = _state.toCommonStateFlow()

    override fun onEvent(event: EpisodeEvent) {
        when(event) {
            is EpisodeEvent.InitWithId -> handleInitEvent(event)
            EpisodeEvent.DislikeEpisode -> handleDislikeEpisodeEvent()
            EpisodeEvent.LikeEpisode -> handleLikeEpisodeEvent()
        }
    }

    private fun handleInitEvent(event: EpisodeEvent.InitWithId) {
        val id = event.id
        if (id == INVALID_ID) {
            _state.update {
                it.copy(
                    error = "Can not find episode with id: $id",
                    isLoading = false,
                )
            }
        } else {
            stateMachineScope.launch {
                getEpisodeUseCase(id = id).collect { resource ->
                    _state.update {
                        it.copy(
                            isLoading = resource == Resource.Loading,
                            error = if (resource is Resource.Error) resource.message else "",
                            data = if (resource is Resource.Success) resource.data else it.data
                        )
                    }
                }
            }
        }
    }

    private fun handleLikeEpisodeEvent() {
        _state.value.data?.let { model ->
            stateMachineScope.launch {
                likeEpisodeUseCase(model = model)
            }
        }
    }

    private fun handleDislikeEpisodeEvent() {
        _state.value.data?.let { model ->
            stateMachineScope.launch {
                dislikeEpisodeUseCase(id = model.id)
            }
        }
    }

}