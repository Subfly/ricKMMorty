package dev.subfly.rickmmorty.data.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import dev.subfly.rickmmorty.ricKMMortyDB

actual class RickAndMortyDatabaseDriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = ricKMMortyDB.Schema,
        name = "ricKMMortyDB"
    )
}