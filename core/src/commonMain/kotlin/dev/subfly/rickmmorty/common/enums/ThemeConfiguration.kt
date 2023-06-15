package dev.subfly.rickmmorty.common.enums

enum class ThemeConfiguration {
    SYSTEM,
    LIGHT,
    DARK
}

fun ThemeConfiguration.toDisplayText(): String {
    return when(this) {
        ThemeConfiguration.SYSTEM -> "Follow System Theme"
        ThemeConfiguration.LIGHT -> "Light Theme"
        ThemeConfiguration.DARK -> "Dark Theme"
    }
}
