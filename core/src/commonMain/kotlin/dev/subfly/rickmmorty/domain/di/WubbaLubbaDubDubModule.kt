package dev.subfly.rickmmorty.domain.di

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.domain.useCases.local.WubbaLubbaDubDub
import org.koin.core.module.Module
import org.koin.dsl.module

val wubbaLubbaDubDubModule: Module = module {
    single {
        WubbaLubbaDubDub(get<RickAndMortyDatabase>())
    }
}