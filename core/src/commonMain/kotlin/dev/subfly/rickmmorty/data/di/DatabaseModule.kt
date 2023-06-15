package dev.subfly.rickmmorty.data.di

import dev.subfly.rickmmorty.data.database.LikedContentManager
import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

val databaseModule: Module = module {
    // get() refers to Driver Factory
    single<RickAndMortyDatabase> { LikedContentManager(get()) }
}