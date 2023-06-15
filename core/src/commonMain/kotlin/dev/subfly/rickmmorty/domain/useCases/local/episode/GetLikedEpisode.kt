package dev.subfly.rickmmorty.domain.useCases.local.episode

import dev.subfly.rickmmorty.domain.mappers.toEpisodeModel
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLikedEpisode(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(
        id: Int
    ): Flow<Resource<EpisodeModel>> = flow {
        emit(Resource.Loading)
        try {
            val entity = database.getLikedEpisode(id = id)
            if (entity == null) {
                emit(Resource.Error(message = "Unable to get episode, please try again..."))
            } else {
                val mapped = entity.toEpisodeModel()
                emit(Resource.Success(data = mapped))
            }
        } catch (e: Exception) {
            e.message?.let {  message ->
                emit(Resource.Error(message = message))
            }
        }
    }
}