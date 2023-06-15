package dev.subfly.rickmmorty.domain.useCases.local.character

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase

class DislikeCharacter (
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(id: Int) = database.removeFromLikedCharacters(id = id)
}