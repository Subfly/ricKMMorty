package dev.subfly.rickmmorty.common.models

import dev.subfly.rickmmorty.common.enums.LocationQueryAcceptField

data class LocationFilterOptions(
    val searchOn: LocationQueryAcceptField = LocationQueryAcceptField.NAME
)
