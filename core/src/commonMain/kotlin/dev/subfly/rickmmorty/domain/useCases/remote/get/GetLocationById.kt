package dev.subfly.rickmmorty.domain.useCases.remote.get

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toLocationModel
import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocationById(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(id: Int) : Flow<Resource<LocationModel>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getLocation(id = id)
            if (response == null) {
                emit(Resource.Error(message = "Unable to get location, please try again..."))
            } else {
                val mapped = response.toLocationModel()
                emit(Resource.Success(data = mapped))
            }
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}