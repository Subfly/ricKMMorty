package dev.subfly.rickmmorty.domain.useCases.local.episode

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase

class DislikeEpisode(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(id: Int) = database.removeFromLikedEpisodes(id = id)
}