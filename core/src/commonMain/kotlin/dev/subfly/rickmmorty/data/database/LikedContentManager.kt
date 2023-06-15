package dev.subfly.rickmmorty.data.database

import app.cash.sqldelight.Query
import app.cash.sqldelight.coroutines.asFlow
import dev.subfly.rickmmorty.CharacterEntity
import dev.subfly.rickmmorty.EpisodeEntity
import dev.subfly.rickmmorty.LocationEntity
import dev.subfly.rickmmorty.ricKMMortyDB
import kotlinx.coroutines.flow.Flow

class LikedContentManager(
    driverFactory: RickAndMortyDatabaseDriverFactory
) : RickAndMortyDatabase {

    private val driver = driverFactory.createDriver()
    private val database = ricKMMortyDB(driver = driver)

    private val charactersQueries = database.characterEntityQueries
    private val locationsQueries = database.locationEntityQueries
    private val episodesQueries = database.episodeEntityQueries

    override fun getAllLikedCharacters(): Flow<Query<CharacterEntity>> {
        return charactersQueries.getAllCharacters().asFlow()
    }

    override fun getAllLikedLocations(): Flow<Query<LocationEntity>> {
        return locationsQueries.getAllLocations().asFlow()
    }

    override fun getAllLikedEpisodes(): Flow<Query<EpisodeEntity>> {
        return episodesQueries.getAllEpisodes().asFlow()
    }

    override fun getLikedCharacter(id: Int): CharacterEntity? {
        return charactersQueries.getCharacter(id = id.toLong()).executeAsOneOrNull()
    }

    override fun getLikedLocation(id: Int): LocationEntity? {
        return locationsQueries.getLocation(id = id.toLong()).executeAsOneOrNull()
    }

    override fun getLikedEpisode(id: Int): EpisodeEntity? {
        return episodesQueries.getEpisode(id = id.toLong()).executeAsOneOrNull()
    }

    override fun likeCharacter(entity: CharacterEntity) {
        charactersQueries.addCharacter(
            id = entity.id,
            name = entity.name,
            status = entity.status,
            species = entity.species,
            type = entity.type,
            gender = entity.gender,
            imageUrl = entity.imageUrl,
            originId = entity.originId,
            originName = entity.originName,
            originType = entity.originType,
            originDimension = entity.originDimension,
            lastLocationId = entity.lastLocationId,
            lastLocationName = entity.lastLocationName,
            lastLocationType = entity.lastLocationType,
            lastLocationDimension = entity.lastLocationDimension
        )
    }

    override fun likeLocation(entity: LocationEntity) {
        locationsQueries.addLocation(
            id = entity.id,
            name = entity.name,
            type = entity.type,
            dimension = entity.dimension
        )
    }

    override fun likeEpisode(entity: EpisodeEntity) {
        episodesQueries.addEpisode(
            id = entity.id,
            name = entity.name,
            airDate = entity.airDate,
            episode = entity.episode
        )
    }

    override fun removeFromLikedCharacters(id: Int) {
        charactersQueries.removeCharacter(id = id.toLong())
    }

    override fun removeFromLikedLocations(id: Int) {
        locationsQueries.removeLocation(id = id.toLong())
    }

    override fun removeFromLikedEpisodes(id: Int) {
        episodesQueries.removeEpisode(id = id.toLong())
    }

    override fun deleteAllLikedCharacters() {
        charactersQueries.nukeDB()
    }

    override fun deleteAllLikedLocations() {
        locationsQueries.nukeDB()
    }

    override fun deleteAllLikedEpisodes() {
        episodesQueries.nukeDB()
    }

    override fun wubbaLubbaDubDub() {
        this.deleteAllLikedCharacters()
        this.deleteAllLikedLocations()
        this.deleteAllLikedEpisodes()
    }
}