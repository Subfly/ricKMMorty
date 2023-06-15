package dev.subfly.rickmmorty.android.common.extensions

val Int?.orZero: Int
    get() = this ?: 0

val Int?.orMinusOne: Int
    get() = this ?: -1