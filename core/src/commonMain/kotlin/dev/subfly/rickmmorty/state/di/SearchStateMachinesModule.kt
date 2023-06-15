package dev.subfly.rickmmorty.state.di

import dev.subfly.rickmmorty.state.search.SearchStateMachine
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.dsl.module

val searchStateMachinesModule: Module = module {
    single<SearchStateMachine> { (context: CoroutineScope) ->
        SearchStateMachine(context)
    }
}