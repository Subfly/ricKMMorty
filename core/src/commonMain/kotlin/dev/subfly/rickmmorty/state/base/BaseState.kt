package dev.subfly.rickmmorty.state.base

interface BaseState<T> {
    val isLoading: Boolean
    val error: String
    val data: T
}
