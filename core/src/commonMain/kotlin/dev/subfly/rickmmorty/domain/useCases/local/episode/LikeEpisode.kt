package dev.subfly.rickmmorty.domain.useCases.local.episode

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.domain.mappers.toEpisodeEntity
import dev.subfly.rickmmorty.common.models.EpisodeModel

class LikeEpisode(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(
        model: EpisodeModel
    ) = database.likeEpisode(entity = model.toEpisodeEntity())
}