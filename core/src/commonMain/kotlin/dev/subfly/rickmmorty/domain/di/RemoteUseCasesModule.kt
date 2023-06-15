package dev.subfly.rickmmorty.domain.di

import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetAllCharacters
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetAllEpisodes
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetAllLocations
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetCharacterById
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetEpisodeById
import dev.subfly.rickmmorty.domain.useCases.remote.get.GetLocationById
import dev.subfly.rickmmorty.domain.useCases.remote.search.SearchCharacter
import dev.subfly.rickmmorty.domain.useCases.remote.search.SearchEpisode
import dev.subfly.rickmmorty.domain.useCases.remote.search.SearchLocation
import org.koin.core.module.Module
import org.koin.dsl.module

val remoteUseCasesFetchModules: Module = module {
    single {
        GetAllCharacters(
            get<RickAndMortyService>()
        )
    }
    single {
        GetAllEpisodes(
            get<RickAndMortyService>()
        )
    }
    single {
        GetAllLocations(
            get<RickAndMortyService>()
        )
    }
    single {
        GetCharacterById(
            get<RickAndMortyService>()
        )
    }
    single {
        GetEpisodeById(
            get<RickAndMortyService>()
        )
    }
    single {
        GetLocationById(
            get<RickAndMortyService>()
        )
    }
}

val remoteUseCasesSearchModules: Module = module {
    single {
        SearchCharacter(
            get<RickAndMortyService>()
        )
    }
    single {
        SearchEpisode(
            get<RickAndMortyService>()
        )
    }
    single {
        SearchLocation(
            get<RickAndMortyService>()
        )
    }
}