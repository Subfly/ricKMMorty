package dev.subfly.rickmmorty.android.di

import dev.subfly.rickmmorty.android.common.managers.ThemeManager
import dev.subfly.rickmmorty.android.common.providers.LikedContentProvider
import dev.subfly.rickmmorty.android.view.container.home.tabs.character.CharacterTabViewModel
import dev.subfly.rickmmorty.android.view.container.home.tabs.episode.EpisodeTabViewModel
import dev.subfly.rickmmorty.android.view.container.home.tabs.location.LocationTabViewModel
import dev.subfly.rickmmorty.android.view.container.liked.LikedContentViewModel
import dev.subfly.rickmmorty.android.view.container.search.SearchedContentViewModel
import dev.subfly.rickmmorty.android.view.detail.character.CharacterDetailViewModel
import dev.subfly.rickmmorty.android.view.detail.episode.EpisodeDetailViewModel
import dev.subfly.rickmmorty.android.view.detail.location.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelsModule: Module = module {
    viewModel { CharacterTabViewModel() }
    viewModel { EpisodeTabViewModel() }
    viewModel { LocationTabViewModel() }
    viewModel { CharacterDetailViewModel() }
    viewModel { EpisodeDetailViewModel() }
    viewModel { LocationDetailViewModel() }
    viewModel { LikedContentViewModel() }
    viewModel { SearchedContentViewModel() }
    viewModel { LikedContentProvider() }
    viewModel { ThemeManager() }
}