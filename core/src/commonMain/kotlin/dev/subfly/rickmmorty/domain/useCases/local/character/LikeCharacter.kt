package dev.subfly.rickmmorty.domain.useCases.local.character

import dev.subfly.rickmmorty.domain.mappers.toCharacterEntity
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.common.models.CharacterModel

class LikeCharacter(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(
        model: CharacterModel
    ) = database.likeCharacter(entity = model.toCharacterEntity())
}