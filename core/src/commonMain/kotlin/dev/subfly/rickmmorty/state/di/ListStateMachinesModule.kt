package dev.subfly.rickmmorty.state.di

import dev.subfly.rickmmorty.state.content.list.characterList.CharacterListStateMachine
import dev.subfly.rickmmorty.state.content.list.episodeList.EpisodeListStateMachine
import dev.subfly.rickmmorty.state.content.list.locationList.LocationListStateMachine
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.dsl.module

val listStateMachinesModule: Module = module {
    factory<CharacterListStateMachine> { (context: CoroutineScope) ->
        CharacterListStateMachine(context)
    }
    factory<EpisodeListStateMachine> { (context: CoroutineScope) ->
        EpisodeListStateMachine(context)
    }
    factory<LocationListStateMachine> { (context: CoroutineScope) ->
        LocationListStateMachine(context)
    }
}