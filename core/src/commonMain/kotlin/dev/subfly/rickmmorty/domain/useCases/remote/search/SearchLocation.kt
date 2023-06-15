package dev.subfly.rickmmorty.domain.useCases.remote.search

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toLocationModel
import dev.subfly.rickmmorty.common.models.LocationFilterOptions
import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchLocation(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(
        page: Int,
        query: String,
        options: LocationFilterOptions
    ) : Flow<Resource<List<LocationModel>>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.searchLocation(
                page = page,
                query = query,
                options = options
            )
            if (response.isEmpty()) {
                emit(Resource.Error(message = "Unable to find location containing '$query'"))
            } else {
                val mapped = response.filterNotNull().map { it.toLocationModel() }
                emit(Resource.Success(mapped))
            }
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}