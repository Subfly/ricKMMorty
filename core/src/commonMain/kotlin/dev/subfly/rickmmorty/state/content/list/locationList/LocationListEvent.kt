package dev.subfly.rickmmorty.state.content.list.locationList

sealed class LocationListEvent {
    object LoadMore: LocationListEvent()
}