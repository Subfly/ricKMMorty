package dev.subfly.rickmmorty.data.di

import com.apollographql.apollo3.ApolloClient
import dev.subfly.rickmmorty.data.network.RickAndMortyRepository
import dev.subfly.rickmmorty.data.network.RickAndMortyService
import dev.subfly.rickmmorty.constants.ApiConstants
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {

    factory<ApolloClient> {
        ApolloClient
            .Builder()
            .serverUrl(ApiConstants.BASE_URL)
            .build()
    }

    // get() refers to Apollo Client
    single<RickAndMortyService> {
        RickAndMortyRepository(get())
    }
}