package dev.subfly.rickmmorty.domain.useCases.local

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase

class WubbaLubbaDubDub(
    private val database: RickAndMortyDatabase
) {
    operator fun invoke() = database.wubbaLubbaDubDub()
}