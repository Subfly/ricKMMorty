package dev.subfly.rickmmorty.data.database

import app.cash.sqldelight.db.SqlDriver

expect class RickAndMortyDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}