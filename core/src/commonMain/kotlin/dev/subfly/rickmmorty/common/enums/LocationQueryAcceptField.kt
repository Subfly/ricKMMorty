package dev.subfly.rickmmorty.common.enums

enum class LocationQueryAcceptField {
    NAME,
    TYPE,
    DIMENSION
}

fun LocationQueryAcceptField.toDisplayText(): String {
    return when(this) {
        LocationQueryAcceptField.NAME -> "Name"
        LocationQueryAcceptField.TYPE -> "Type"
        LocationQueryAcceptField.DIMENSION -> "Dimension"
    }
}