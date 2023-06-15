package dev.subfly.rickmmorty.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloHttpException
import com.apollographql.apollo3.exception.ApolloNetworkException
import dev.subfly.rickmmorty.GetCharacterQuery
import dev.subfly.rickmmorty.GetCharactersQuery
import dev.subfly.rickmmorty.GetEpisodeQuery
import dev.subfly.rickmmorty.GetEpisodesQuery
import dev.subfly.rickmmorty.GetLocationQuery
import dev.subfly.rickmmorty.GetLocationsQuery
import dev.subfly.rickmmorty.common.enums.CharacterQueryAcceptField
import dev.subfly.rickmmorty.common.enums.EpisodeQueryAcceptField
import dev.subfly.rickmmorty.common.enums.LocationQueryAcceptField
import dev.subfly.rickmmorty.common.models.CharacterFilterOptions
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.common.models.LocationFilterOptions
import dev.subfly.rickmmorty.type.FilterCharacter
import dev.subfly.rickmmorty.type.FilterEpisode
import dev.subfly.rickmmorty.type.FilterLocation
import dev.subfly.rickmmorty.common.utils.NetworkErrors

class RickAndMortyRepository(
    private val client: ApolloClient
) : RickAndMortyService {

    override suspend fun getCharacters(page: Int): List<GetCharactersQuery.CharacterDTO?> {
        val response = client.query(
            GetCharactersQuery(
                page = Optional.present(page)
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.characters?.characterDTO ?: emptyList()
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun getLocations(page: Int): List<GetLocationsQuery.LocationDTO?> {
        val response = client.query(
            GetLocationsQuery(
                page = Optional.present(page)
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.locations?.locationDTO ?: emptyList()
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun getEpisodes(page: Int): List<GetEpisodesQuery.EpisodeDTO?> {
        val response = client.query(
            GetEpisodesQuery(
                page = Optional.present(page)
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.episodes?.episodeDTO ?: emptyList()
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun getCharacter(id: Int): GetCharacterQuery.CharacterDTO? {
        val response = client.query(
            GetCharacterQuery(
                id = Optional.present(id.toString())
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.characterDTO
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun getLocation(id: Int): GetLocationQuery.LocationDTO? {
        val response = client.query(
            GetLocationQuery(
                id = Optional.present(id.toString())
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.locationDTO
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun getEpisode(id: Int): GetEpisodeQuery.EpisodeDTO? {
        val response = client.query(
            GetEpisodeQuery(
                id = Optional.present(id.toString())
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.episodeDTO
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun searchCharacter(
        page: Int,
        query: String,
        options: CharacterFilterOptions
    ): List<GetCharactersQuery.CharacterDTO?> {
        val response = client.query(
            GetCharactersQuery(
                page = Optional.present(page),
                filter = Optional.present(
                    value = FilterCharacter(
                        status = Optional.present(options.status.value),
                        gender = Optional.present(options.gender.value),
                        name = if (options.searchOn == CharacterQueryAcceptField.NAME)
                            Optional.present(query)
                        else
                            Optional.absent(),
                        species = if (options.searchOn == CharacterQueryAcceptField.SPECIES)
                            Optional.present(query)
                        else
                            Optional.absent(),
                        type = if (options.searchOn == CharacterQueryAcceptField.TYPE)
                            Optional.present(query)
                        else
                            Optional.absent()
                    )
                )
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.characters?.characterDTO ?: emptyList()
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun searchLocation(
        page: Int,
        query: String,
        options: LocationFilterOptions
    ): List<GetLocationsQuery.LocationDTO?> {
        val response = client.query(
            GetLocationsQuery(
                page = Optional.present(page),
                filter = Optional.present(
                    value = FilterLocation(
                        name = if (options.searchOn == LocationQueryAcceptField.NAME)
                            Optional.present(query)
                        else
                            Optional.absent(),
                        type = if (options.searchOn == LocationQueryAcceptField.TYPE)
                            Optional.present(query)
                        else
                            Optional.absent(),
                        dimension = if (options.searchOn == LocationQueryAcceptField.DIMENSION)
                            Optional.present(query)
                        else
                            Optional.absent()
                    )
                )
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.locations?.locationDTO ?: emptyList()
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

    override suspend fun searchEpisode(
        page: Int,
        query: String,
        options: EpisodeFilterOptions
    ): List<GetEpisodesQuery.EpisodeDTO?> {
        val response = client.query(
            GetEpisodesQuery(
                page = Optional.present(page),
                filter = Optional.present(
                    value = FilterEpisode(
                        name = if (options.searchOn == EpisodeQueryAcceptField.NAME)
                            Optional.present(query)
                        else
                            Optional.absent(),
                        episode = if (options.searchOn == EpisodeQueryAcceptField.EPISODE_CODE)
                            Optional.present(query)
                        else
                            Optional.absent()
                    )
                )
            )
        ).execute()
        val data = response.data
        if (data != null) {
            return data.episodes?.episodeDTO ?: emptyList()
        } else {
            when (response.exception) {
                is ApolloHttpException -> throw NetworkErrors.HTTPException
                is ApolloNetworkException -> throw NetworkErrors.NetworkException
                else -> throw NetworkErrors.UnknownError
            }
        }
    }

}