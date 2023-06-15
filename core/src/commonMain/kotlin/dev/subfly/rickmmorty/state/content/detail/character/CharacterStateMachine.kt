package dev.subfly.rickmmorty.state.content.detail.character

import dev.subfly.rickmmorty.common.utils.toCommonStateFlow
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetCharacterById
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.domain.useCases.local.character.DislikeCharacter
import dev.subfly.rickmmorty.domain.useCases.local.character.LikeCharacter
import dev.subfly.rickmmorty.state.base.BaseStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class CharacterStateMachine(
    scope: CoroutineScope? = null
): BaseStateMachine<CharacterEvent>(coroutineScope = scope) {

    private val getCharacterUseCase: GetCharacterById by inject()
    private val likeCharacterUseCase:  LikeCharacter by inject()
    private val dislikeCharacterUseCase: DislikeCharacter by inject()

    private val _state = MutableStateFlow(CharacterState())
    val currentState = _state.toCommonStateFlow()

    override fun onEvent(event: CharacterEvent) {
        when(event) {
            is CharacterEvent.InitWithId -> handleInitEvent(event)
            is CharacterEvent.DislikeCharacter -> handleDislikeCharacterEvent()
            is CharacterEvent.LikeCharacter -> handleLikeCharacterEvent()
        }
    }

    private fun handleInitEvent(event: CharacterEvent.InitWithId) {
        val id = event.id
        if (id == INVALID_ID) {
            _state.update {
                it.copy(
                    error = "Can not find character with id: $id",
                    isLoading = false,
                )
            }
        } else {
            stateMachineScope.launch {
                getCharacterUseCase(id = id).collect { resource ->
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

    private fun handleLikeCharacterEvent() {
        _state.value.data?.let { model ->
            stateMachineScope.launch {
                likeCharacterUseCase(model = model)
            }
        }
    }

    private fun handleDislikeCharacterEvent() {
        _state.value.data?.let { model ->
            stateMachineScope.launch {
                dislikeCharacterUseCase(id = model.id)
            }
        }
    }

}