package dev.subfly.rickmmorty.state.di

import dev.subfly.rickmmorty.state.theme.ThemeStateMachine
import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.Module
import org.koin.dsl.module

val themeStateMachinesModule: Module = module {
    single<ThemeStateMachine> { (context: CoroutineScope) ->
        ThemeStateMachine(context)
    }
}