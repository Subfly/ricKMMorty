package dev.subfly.rickmmorty.data.network

import dev.subfly.rickmmorty.GetCharacterQuery
import dev.subfly.rickmmorty.GetCharactersQuery
import dev.subfly.rickmmorty.GetEpisodeQuery
import dev.subfly.rickmmorty.GetEpisodesQuery
import dev.subfly.rickmmorty.GetLocationQuery
import dev.subfly.rickmmorty.GetLocationsQuery
import dev.subfly.rickmmorty.common.models.CharacterFilterOptions
import dev.subfly.rickmmorty.common.models.EpisodeFilterOptions
import dev.subfly.rickmmorty.common.models.LocationFilterOptions

interface RickAndMortyService {
    suspend fun getCharacters(page: Int): List<GetCharactersQuery.CharacterDTO?>
    suspend fun getLocations(page: Int): List<GetLocationsQuery.LocationDTO?>
    suspend fun getEpisodes(page: Int): List<GetEpisodesQuery.EpisodeDTO?>
    suspend fun getCharacter(id: Int): GetCharacterQuery.CharacterDTO?
    suspend fun getLocation(id: Int): GetLocationQuery.LocationDTO?
    suspend fun getEpisode(id: Int): GetEpisodeQuery.EpisodeDTO?
    suspend fun searchCharacter(page: Int, query: String, options: CharacterFilterOptions): List<GetCharactersQuery.CharacterDTO?>
    suspend fun searchLocation(page: Int, query: String, options: LocationFilterOptions): List<GetLocationsQuery.LocationDTO?>
    suspend fun searchEpisode(page: Int, query: String, options: EpisodeFilterOptions): List<GetEpisodesQuery.EpisodeDTO?>
}