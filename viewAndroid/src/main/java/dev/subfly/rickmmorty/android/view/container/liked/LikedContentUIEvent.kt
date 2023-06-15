package dev.subfly.rickmmorty.android.view.container.liked

sealed class LikedContentUIEvent {
    object CharacterFilterPressed: LikedContentUIEvent()
    object EpisodeFilterPressed: LikedContentUIEvent()
    object LocationFilterPressed: LikedContentUIEvent()
}