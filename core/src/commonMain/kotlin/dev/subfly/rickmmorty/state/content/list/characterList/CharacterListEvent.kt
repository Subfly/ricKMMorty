package dev.subfly.rickmmorty.state.content.list.characterList

sealed class CharacterListEvent {
    object LoadMore: CharacterListEvent()
}