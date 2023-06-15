package dev.subfly.rickmmorty.state.di

import dev.subfly.rickmmorty.state.content.detail.character.CharacterStateMachine
import dev.subfly.rickmmorty.state.content.detail.episode.EpisodeStateMachine
import dev.subfly.rickmmorty.state.content.detail.location.LocationStateMachine
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.dsl.module

val detailStateMachinesModule: Module = module {
    factory<CharacterStateMachine> { (context: CoroutineScope) ->
        CharacterStateMachine(context)
    }
    factory<LocationStateMachine> { (context: CoroutineScope) ->
        LocationStateMachine(context)
    }
    factory<EpisodeStateMachine> { (context: CoroutineScope) ->
        EpisodeStateMachine(context)
    }
}