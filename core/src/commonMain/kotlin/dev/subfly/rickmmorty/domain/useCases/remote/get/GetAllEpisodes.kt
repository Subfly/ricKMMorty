package dev.subfly.rickmmorty.domain.useCases.remote.get

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toEpisodeModel
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllEpisodes(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(page: Int) : Flow<Resource<List<EpisodeModel>>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getEpisodes(page = page)
            val mapped = response.filterNotNull().map { it.toEpisodeModel() }
            emit(Resource.Success(data = mapped))
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}