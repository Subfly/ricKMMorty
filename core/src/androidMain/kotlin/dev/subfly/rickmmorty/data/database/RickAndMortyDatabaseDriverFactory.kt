package dev.subfly.rickmmorty.data.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.subfly.rickmmorty.ricKMMortyDB

actual class RickAndMortyDatabaseDriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        context = context,
        schema = ricKMMortyDB.Schema,
        name = "ricKMMortyDB"
    )
}