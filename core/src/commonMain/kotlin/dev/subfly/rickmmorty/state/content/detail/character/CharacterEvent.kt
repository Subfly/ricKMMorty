package dev.subfly.rickmmorty.state.content.detail.character

import dev.subfly.rickmmorty.common.models.CharacterModel

sealed class CharacterEvent {
    data class InitWithId(val id: Int): CharacterEvent()
    object LikeCharacter: CharacterEvent()
    object DislikeCharacter: CharacterEvent()
}