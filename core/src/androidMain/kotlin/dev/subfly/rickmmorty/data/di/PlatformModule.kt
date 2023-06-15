package dev.subfly.rickmmorty.data.di

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabaseDriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    // get() refers to Android Context
    single { RickAndMortyDatabaseDriverFactory(get()) }
}