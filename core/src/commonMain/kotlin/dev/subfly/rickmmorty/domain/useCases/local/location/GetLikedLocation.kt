package dev.subfly.rickmmorty.domain.useCases.local.location

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.domain.mappers.toLocationModel
import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.common.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLikedLocation(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(
        id: Int
    ): Flow<Resource<LocationModel>> = flow {
        emit(Resource.Loading)
        try {
            val entity = database.getLikedLocation(id = id)
            if (entity == null) {
                emit(Resource.Error(message = "Unable to get location, please try again..."))
            } else {
                val mapped = entity.toLocationModel()
                emit(Resource.Success(data = mapped))
            }
        } catch (e: Exception) {
            e.message?.let {  message ->
                emit(Resource.Error(message = message))
            }
        }
    }
}