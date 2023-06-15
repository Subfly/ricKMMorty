package dev.subfly.rickmmorty.state.content.detail.episode

sealed class EpisodeEvent {
    data class InitWithId(val id: Int): EpisodeEvent()
    object LikeEpisode: EpisodeEvent()
    object DislikeEpisode: EpisodeEvent()
}