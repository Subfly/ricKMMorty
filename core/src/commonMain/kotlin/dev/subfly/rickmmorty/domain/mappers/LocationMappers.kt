package dev.subfly.rickmmorty.domain.mappers

import dev.subfly.rickmmorty.GetCharacterQuery
import dev.subfly.rickmmorty.GetLocationQuery
import dev.subfly.rickmmorty.GetLocationsQuery
import dev.subfly.rickmmorty.LocationEntity
import dev.subfly.rickmmorty.common.models.LocationModel

/**
LocationDTO resembles to LocationModel can be used in Location Detail
 */
fun GetLocationQuery.LocationDTO.toLocationModel(): LocationModel {
    return LocationModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        dimension = dimension.orEmpty(),
        type = type.orEmpty(),
        residents = characterDTOFilterNotNull().map { it.toCharacterModel() }
    )
}

/**
LocationDTO resembles to LocationModel can be used in List Item
 */
fun GetLocationsQuery.LocationDTO.toLocationModel(): LocationModel {
    return LocationModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        dimension = dimension.orEmpty(),
        type = type.orEmpty()
    )
}

/**
LocationDTO resembles to LocationModel can be used in List Item
 */
fun GetCharacterQuery.LastLocationDTO.toLocationModel(): LocationModel {
    return LocationModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        dimension = dimension.orEmpty(),
        type = type.orEmpty()
    )
}

/**
LocationDTO resembles to LocationModel can be used in List Item
 */
fun GetCharacterQuery.OriginLocationDTO.toLocationModel(): LocationModel {
    return LocationModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        dimension = dimension.orEmpty(),
        type = type.orEmpty()
    )
}

fun LocationEntity.toLocationModel(): LocationModel {
    return LocationModel(
        id = id.toInt(),
        name = name.orEmpty(),
        type = type.orEmpty(),
        dimension = dimension.orEmpty()
    )
}

fun LocationModel.toLocationEntity(): LocationEntity {
    return LocationEntity(
        id = id.toLong(),
        name = name,
        type = type,
        dimension = dimension
    )
}