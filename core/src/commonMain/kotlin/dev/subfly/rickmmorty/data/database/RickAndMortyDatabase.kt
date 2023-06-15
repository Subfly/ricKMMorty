package dev.subfly.rickmmorty.data.database

import app.cash.sqldelight.Query
import dev.subfly.rickmmorty.CharacterEntity
import dev.subfly.rickmmorty.EpisodeEntity
import dev.subfly.rickmmorty.LocationEntity
import kotlinx.coroutines.flow.Flow

interface RickAndMortyDatabase {

    fun getAllLikedCharacters(): Flow<Query<CharacterEntity>>
    fun getAllLikedLocations(): Flow<Query<LocationEntity>>
    fun getAllLikedEpisodes(): Flow<Query<EpisodeEntity>>

    fun getLikedCharacter(id: Int): CharacterEntity?
    fun getLikedLocation(id: Int): LocationEntity?
    fun getLikedEpisode(id: Int): EpisodeEntity?

    fun likeCharacter(entity: CharacterEntity)
    fun likeLocation(entity: LocationEntity)
    fun likeEpisode(entity: EpisodeEntity)

    fun removeFromLikedCharacters(id: Int)
    fun removeFromLikedLocations(id: Int)
    fun removeFromLikedEpisodes(id: Int)

    fun deleteAllLikedCharacters()
    fun deleteAllLikedLocations()
    fun deleteAllLikedEpisodes()

    fun wubbaLubbaDubDub()

}