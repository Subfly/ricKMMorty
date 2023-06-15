package dev.subfly.rickmmorty.domain.useCases.remote.get

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toEpisodeModel
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetEpisodeById(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(id: Int) : Flow<Resource<EpisodeModel>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getEpisode(id = id)
            if (response == null) {
                emit(Resource.Error(message = "Unable to get episode, please try again..."))
            } else {
                val mapped = response.toEpisodeModel()
                emit(Resource.Success(data = mapped))
            }
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}