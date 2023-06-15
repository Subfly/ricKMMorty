package dev.subfly.rickmmorty.domain.useCases.local.episode

import app.cash.sqldelight.coroutines.mapToList
import dev.subfly.rickmmorty.domain.mappers.toEpisodeModel
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class GetLikedEpisodes(
    private val database: RickAndMortyDatabase,
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(): Flow<Resource<List<EpisodeModel>>> = flow {
        emit(Resource.Loading)
        try {
            database.getAllLikedEpisodes().mapToList(coroutineContext).collect { entities ->
                val mapped = entities.map { it.toEpisodeModel() }
                emit(Resource.Success(data = mapped))
            }
        } catch (e: Exception) {
            e.message?.let { message ->
                emit(Resource.Error(message = message))
            }
        }
    }
}