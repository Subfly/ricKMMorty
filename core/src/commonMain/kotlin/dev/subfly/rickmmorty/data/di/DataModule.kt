package dev.subfly.rickmmorty.data.di

val dataModules = listOf(
    platformModule(),
    databaseModule,
    networkModule
)