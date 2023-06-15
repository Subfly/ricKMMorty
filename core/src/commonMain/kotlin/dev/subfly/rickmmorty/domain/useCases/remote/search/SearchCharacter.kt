package dev.subfly.rickmmorty.domain.useCases.remote.search

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toCharacterModel
import dev.subfly.rickmmorty.common.models.CharacterFilterOptions
import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchCharacter(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(
        page: Int,
        query: String,
        options: CharacterFilterOptions
    ) : Flow<Resource<List<CharacterModel>>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.searchCharacter(
                page = page,
                query = query,
                options = options
            )
            if (response.isEmpty()) {
                emit(Resource.Error(message = "Unable to find character containing '$query'"))
            } else {
                val mapped = response.filterNotNull().map { it.toCharacterModel() }
                emit(Resource.Success(mapped))
            }
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}