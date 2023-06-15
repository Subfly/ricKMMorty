package dev.subfly.rickmmorty.domain.useCases.local.location

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.domain.mappers.toLocationEntity
import dev.subfly.rickmmorty.common.models.LocationModel

class LikeLocation(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke(
        model: LocationModel
    ) = database.likeLocation(entity = model.toLocationEntity())
}