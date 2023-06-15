package dev.subfly.rickmmorty.state.di

import dev.subfly.rickmmorty.state.liked.LikedContentStateMachine
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.dsl.module

val likedContentStateMachinesModule: Module = module {
    single<LikedContentStateMachine> { (context: CoroutineScope) ->
        LikedContentStateMachine(context)
    }
}