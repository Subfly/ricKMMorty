package dev.subfly.rickmmorty.android.common.extensions

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import dev.subfly.rickmmorty.android.navigation.base.BaseDestination

fun NavHostController.navigate(to: BaseDestination) {
    this.navigate(to.route)
}

fun NavHostController.navigate(to: BaseDestination, withOptions: NavOptionsBuilder.() -> Unit) {
    this.navigate(to.route, withOptions)
}