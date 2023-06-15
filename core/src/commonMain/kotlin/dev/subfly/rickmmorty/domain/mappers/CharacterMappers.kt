package dev.subfly.rickmmorty.domain.mappers

import dev.subfly.rickmmorty.CharacterEntity
import dev.subfly.rickmmorty.GetCharacterQuery
import dev.subfly.rickmmorty.GetCharactersQuery
import dev.subfly.rickmmorty.GetEpisodeQuery
import dev.subfly.rickmmorty.GetLocationQuery
import dev.subfly.rickmmorty.common.enums.toCharacterGender
import dev.subfly.rickmmorty.common.enums.toCharacterStatus
import dev.subfly.rickmmorty.common.models.CharacterModel
import dev.subfly.rickmmorty.common.models.LocationModel

/**
CharacterDTO resembles to CharacterModel can be used in Character Detail
 */
fun GetCharacterQuery.CharacterDTO.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        status = status.toCharacterStatus(),
        species = species.orEmpty(),
        type = type.orEmpty(),
        gender = gender.toCharacterGender(),
        imageUrl = image.orEmpty(),
        originLocation = originLocationDTO?.toLocationModel(),
        lastLocation = lastLocationDTO?.toLocationModel(),
        episodes = episodeListDTOFilterNotNull().map { it.toEpisodeModel() }
    )
}

/**
CharacterDTO resembles to CharacterModel can be used in List Item
 */
fun GetCharactersQuery.CharacterDTO.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        status = status.toCharacterStatus(),
        species = species.orEmpty(),
        imageUrl = image.orEmpty()
    )
}

/**
CharacterDTO resembles to CharacterModel can be used in List Item
 */
fun GetEpisodeQuery.CharacterDTO.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        status = status.toCharacterStatus(),
        species = species.orEmpty(),
        imageUrl = image.orEmpty()
    )
}

/**
CharacterDTO resembles to CharacterModel can be used in List Item
 */
fun GetLocationQuery.CharacterDTO.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id?.toInt() ?: -1,
        name = name.orEmpty(),
        status = status.toCharacterStatus(),
        species = species.orEmpty(),
        imageUrl = image.orEmpty()
    )
}

fun CharacterEntity.toCharacterModel(): CharacterModel {
    return CharacterModel(
        id = id.toInt(),
        name = name.orEmpty(),
        status = status.toCharacterStatus(),
        species = species.orEmpty(),
        type = type.orEmpty(),
        gender = gender.toCharacterGender(),
        imageUrl = imageUrl.orEmpty(),
        originLocation = LocationModel(
            id = originId.toInt(),
            name = originName.orEmpty(),
            type = originType.orEmpty(),
            dimension = originDimension.orEmpty()
        ),
        lastLocation = LocationModel(
            id = lastLocationId.toInt(),
            name = lastLocationName.orEmpty(),
            type = lastLocationType.orEmpty(),
            dimension = lastLocationDimension.orEmpty()
        )
    )
}

fun CharacterModel.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id.toLong(),
        name = name,
        status = status.value,
        species = species,
        type = type,
        gender = gender.value,
        imageUrl = imageUrl,
        originId = originLocation?.id?.toLong() ?: -1L,
        originName = originLocation?.name,
        originType = originLocation?.type,
        originDimension = originLocation?.dimension,
        lastLocationId = lastLocation?.id?.toLong() ?: -1L,
        lastLocationName = lastLocation?.name,
        lastLocationType = lastLocation?.type,
        lastLocationDimension = lastLocation?.dimension
    )
}