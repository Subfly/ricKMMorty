package dev.subfly.rickmmorty.di

import dev.subfly.rickmmorty.data.di.dataModules
import dev.subfly.rickmmorty.domain.di.domainModules
import dev.subfly.rickmmorty.state.di.stateModules
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

object DIHelper {
    private fun allModules() = dataModules + domainModules + stateModules

    fun initKoin(
        appDeclaration: KoinAppDeclaration = {}
    ) = startKoin {
        appDeclaration()
        modules(allModules())
    }

    // For iOS
    fun initKoin() = initKoin {}
}