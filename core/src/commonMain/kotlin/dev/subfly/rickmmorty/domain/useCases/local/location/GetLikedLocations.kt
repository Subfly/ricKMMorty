package dev.subfly.rickmmorty.domain.useCases.local.location

import app.cash.sqldelight.coroutines.mapToList
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.domain.mappers.toLocationModel
import dev.subfly.rickmmorty.common.models.LocationModel
import dev.subfly.rickmmorty.common.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

class GetLikedLocations(
    private val database: RickAndMortyDatabase,
    private val coroutineContext: CoroutineContext
) {
    operator fun invoke(): Flow<Resource<List<LocationModel>>> = flow {
        emit(Resource.Loading)
        try {
            database.getAllLikedLocations()
                .mapToList(coroutineContext).collect { entities ->
                val mapped = entities.map { it.toLocationModel() }
                emit(Resource.Success(data = mapped))
            }
        } catch (e: Exception) {
            e.message?.let { message ->
                emit(Resource.Error(message = message))
            }
        }
    }
}