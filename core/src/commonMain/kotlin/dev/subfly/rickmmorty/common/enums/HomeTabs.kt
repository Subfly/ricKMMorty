package dev.subfly.rickmmorty.common.enums

enum class HomeTab(
    val title: String,
    val route: String
) {

    CHARACTERS(
        title = "Characters",
        route = "characters_route"
    ),

    EPISODES(
        title = "Episodes",
        route = "episodes_route"
    ),

    LOCATIONS(
        title = "Locations",
        route = "locations_route"
    )

}
