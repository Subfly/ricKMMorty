package dev.subfly.rickmmorty.state.content.detail.location

sealed class LocationEvent {
    data class InitWithId(val id: Int): LocationEvent()
    object LikeLocation: LocationEvent()
    object DislikeLocation: LocationEvent()
}