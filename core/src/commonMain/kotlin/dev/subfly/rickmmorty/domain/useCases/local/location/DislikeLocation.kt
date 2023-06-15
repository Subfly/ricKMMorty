package dev.subfly.rickmmorty.domain.useCases.local.location

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase


class DislikeLocation(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(id: Int) = database.removeFromLikedLocations(id = id)
}