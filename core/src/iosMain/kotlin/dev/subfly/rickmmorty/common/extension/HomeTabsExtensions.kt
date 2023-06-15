package dev.subfly.rickmmorty.common.extension

import dev.subfly.rickmmorty.common.enums.HomeTab

fun HomeTab.getIcon(): String {
    return when(this) {
        HomeTab.CHARACTERS -> "house"
        HomeTab.EPISODES -> "tv"
        HomeTab.LOCATIONS -> "globe.europe.africa"
    }
}
