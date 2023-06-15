package dev.subfly.rickmmorty.domain.mappers

import dev.subfly.rickmmorty.EpisodeEntity
import dev.subfly.rickmmorty.GetCharacterQuery
import dev.subfly.rickmmorty.GetEpisodeQuery
import dev.subfly.rickmmorty.GetEpisodesQuery
import dev.subfly.rickmmorty.common.models.EpisodeModel

/**
EpisodeDTO resembles to EpisodeModel can be used in Episode Detail
 */
fun GetEpisodeQuery.EpisodeDTO.toEpisodeModel(): EpisodeModel {
    return EpisodeModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        episode = episode.orEmpty(),
        airDate = air_date.orEmpty(),
        characters = characterDTOFilterNotNull().map { it.toCharacterModel() }
    )
}

/**
EpisodeDTO resembles to EpisodeModel can be used in List Item
 */
fun GetEpisodesQuery.EpisodeDTO.toEpisodeModel(): EpisodeModel {
    return EpisodeModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        episode = episode.orEmpty(),
        airDate = air_date.orEmpty()
    )
}

/**
EpisodeDTO resembles to EpisodeModel can be used in List Item
 */
fun GetCharacterQuery.EpisodeListDTO.toEpisodeModel(): EpisodeModel {
    return EpisodeModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        episode = episode.orEmpty(),
        airDate = air_date.orEmpty()
    )
}

fun EpisodeEntity.toEpisodeModel(): EpisodeModel {
    return EpisodeModel(
        id = id.toInt(),
        name = name.orEmpty(),
        airDate = airDate.orEmpty(),
        episode = episode.orEmpty()
    )
}

fun EpisodeModel.toEpisodeEntity(): EpisodeEntity {
    return EpisodeEntity(
        id = id.toLong(),
        name = name,
        airDate = airDate,
        episode = episode
    )
}