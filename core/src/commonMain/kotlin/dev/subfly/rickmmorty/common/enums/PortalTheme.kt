package dev.subfly.rickmmorty.common.enums

enum class PortalTheme {
    BLUE,
    GREEN,
    YELLOW
}

fun PortalTheme.toDisplayText(): String {
    return when(this) {
        PortalTheme.BLUE -> "Blue Portal"
        PortalTheme.GREEN -> "Green Portal"
        PortalTheme.YELLOW -> "Yellow Portal"
    }
}
