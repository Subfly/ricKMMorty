package dev.subfly.rickmmorty.state.content.list.episodeList

sealed class EpisodeListEvent {
    object LoadMore: EpisodeListEvent()
}