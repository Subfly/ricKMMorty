package dev.subfly.rickmmorty.domain.useCases.remote.search

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toEpisodeModel
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.common.models.EpisodeModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchEpisode(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(
        page: Int,
        query: String,
        options: EpisodeFilterOptions
    ) : Flow<Resource<List<EpisodeModel>>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.searchEpisode(
                page = page,
                query = query,
                options = options
            )
            if (response.isEmpty()) {
                emit(Resource.Error(message = "Unable to find episode containing '$query'"))
            } else {
                val mapped = response.filterNotNull().map { it.toEpisodeModel() }
                emit(Resource.Success(mapped))
            }
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}