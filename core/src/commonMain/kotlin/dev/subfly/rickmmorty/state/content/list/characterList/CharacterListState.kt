package dev.subfly.rickmmorty.state.content.list.characterList

import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.state.base.BaseState

data class CharacterListState(
    override val isLoading: Boolean = true,
    override val error: String = "",
    override val data: List<CharacterModel> = emptyList()
): BaseState<List<CharacterModel>>