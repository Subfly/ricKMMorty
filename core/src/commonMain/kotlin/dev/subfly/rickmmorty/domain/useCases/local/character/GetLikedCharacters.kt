package dev.subfly.rickmmorty.domain.useCases.local.character

import app.cash.sqldelight.coroutines.mapToList
import dev.subfly.rickmmorty.domain.mappers.toCharacterModel
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class GetLikedCharacters(
    private val database: RickAndMortyDatabase,
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(): Flow<Resource<List<CharacterModel>>> = flow {
        emit(Resource.Loading)
        try {
            database.getAllLikedCharacters().mapToList(coroutineContext).collect { entities ->
                val mapped = entities.map { it.toCharacterModel() }
                emit(Resource.Success(data = mapped))
            }
        } catch (e: Exception) {
            e.message?.let {  message ->
                emit(Resource.Error(message = message))
            }
        }
    }
}