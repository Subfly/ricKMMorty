package dev.subfly.rickmmorty.domain.di

import dev.subfly.rickmmorty.data.database.RickAndMortyDatabase
import dev.subfly.rickmmorty.domain.useCases.local.character.DislikeCharacter
import dev.subfly.rickmmorty.domain.useCases.local.character.GetLikedCharacter
import dev.subfly.rickmmorty.domain.useCases.local.character.GetLikedCharacters
import dev.subfly.rickmmorty.domain.useCases.local.character.LikeCharacter
import dev.subfly.rickmmorty.domain.useCases.local.episode.DislikeEpisode
import dev.subfly.rickmmorty.domain.useCases.local.episode.GetLikedEpisode
import dev.subfly.rickmmorty.domain.useCases.local.episode.GetLikedEpisodes
import dev.subfly.rickmmorty.domain.useCases.local.episode.LikeEpisode
import dev.subfly.rickmmorty.domain.useCases.local.location.DislikeLocation
import dev.subfly.rickmmorty.domain.useCases.local.location.GetLikedLocation
import dev.subfly.rickmmorty.domain.useCases.local.location.GetLikedLocations
import dev.subfly.rickmmorty.domain.useCases.local.location.LikeLocation
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val localUseCasesCharacterModules: Module = module {
    // All get()'s resembles to RickAndMortyDatabase
    single { (context: CoroutineContext) ->
        GetLikedCharacters(
            get<RickAndMortyDatabase>(),
            coroutineContext = context
        )
    }
    single {
        GetLikedCharacter(
            get<RickAndMortyDatabase>()
        )
    }
    single {
        LikeCharacter(
            get<RickAndMortyDatabase>()
        )
    }
    single {
        DislikeCharacter(
            get<RickAndMortyDatabase>()
        )
    }
}

val localUseCasesEpisodeModules: Module = module {
    // All get()'s resembles to RickAndMortyDatabase
    single {(context: CoroutineContext) ->
        GetLikedEpisodes(
            get<RickAndMortyDatabase>(),
            coroutineContext = context
        )
    }
    single {
        GetLikedEpisode(
            get<RickAndMortyDatabase>()
        )
    }
    single {
        LikeEpisode(
            get<RickAndMortyDatabase>()
        )
    }
    single {
        DislikeEpisode(
            get<RickAndMortyDatabase>()
        )
    }
}

val localUseCasesLocationModules: Module = module {
    // All get()'s resembles to RickAndMortyDatabase
    single {(context: CoroutineContext) ->
        GetLikedLocations(
            get<RickAndMortyDatabase>(),
            coroutineContext = context
        )
    }
    single {
        GetLikedLocation(
            get<RickAndMortyDatabase>()
        )
    }
    single {
        LikeLocation(
            get<RickAndMortyDatabase>()
        )
    }
    single {
        DislikeLocation(
            get<RickAndMortyDatabase>()
        )
    }
}