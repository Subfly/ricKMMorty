package dev.subfly.rickmmorty.domain.useCases.remote.get

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.mappers.toCharacterModel
import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.Resource
import dev.subfly.rickmmorty.common.utils.NetworkErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCharacterById(
    private val service: RickAndMortyService
) {
    suspend operator fun invoke(id: Int) : Flow<Resource<CharacterModel>> = flow {
        emit(Resource.Loading)
        try {
            val response = service.getCharacter(id = id)
            if (response == null) {
                emit(Resource.Error(message = "Unable to get character, please try again..."))
            } else {
                val mapped = response.toCharacterModel()
                emit(Resource.Success(data = mapped))
            }
        } catch (e: NetworkErrors) {
            emit(Resource.Error(message = e.message))
        }
    }
}