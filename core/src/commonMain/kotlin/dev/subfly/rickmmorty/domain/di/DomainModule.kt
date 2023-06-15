package dev.subfly.rickmmorty.domain.di

val domainModules = listOf(
    localUseCasesCharacterModules,
    localUseCasesEpisodeModules,
    localUseCasesLocationModules,
    remoteUseCasesFetchModules,
    remoteUseCasesSearchModules,
    wubbaLubbaDubDubModule
)