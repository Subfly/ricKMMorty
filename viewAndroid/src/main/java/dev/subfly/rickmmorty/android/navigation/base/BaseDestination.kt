package dev.subfly.rickmmorty.android.navigation.base

import androidx.navigation.NamedNavArgument
import dev.subfly.rickmmorty.android.common.enums.NavParams

abstract class BaseDestination: ScreenRouted {
    abstract val route: String
    abstract val arguments: List<NamedNavArgument>

    internal fun createRoute(
        route: String,
        parameters: List<NavParams> = emptyList()
    ): String {
        var head = "$route?"
        parameters.forEachIndexed { index, param ->
            head += param.routeParameter
            if(index != parameters.lastIndex)
                head += "&"
        }
        return head
    }

    internal fun createRouteWithParams(
        route: String,
        parameters: Map<NavParams, Any> = emptyMap(),
    ): String {
        var head = "$route?"
        parameters.onEachIndexed { index, (key, value) ->
            head += "${key.argName}=${value}"
            if(index != arguments.lastIndex)
                head += "&"
        }
        return head
    }
}