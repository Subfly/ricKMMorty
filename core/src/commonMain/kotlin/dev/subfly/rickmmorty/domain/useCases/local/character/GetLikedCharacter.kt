package dev.subfly.rickmmorty.domain.useCases.local.character

import dev.subfly.rickmmorty.domain.mappers.toCharacterModel
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLikedCharacter(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(
        id: Int
    ): Flow<Resource<CharacterModel>> = flow {
        emit(Resource.Loading)
        try {
            val entity = database.getLikedCharacter(id = id)
            if (entity == null) {
                emit(Resource.Error(message = "Unable to get character, please try again..."))
            } else {
                val mapped = entity.toCharacterModel()
                emit(Resource.Success(data = mapped))
            }
        } catch (e: Exception) {
            e.message?.let {  message ->
                emit(Resource.Error(message = message))
            }
        }
    }
}