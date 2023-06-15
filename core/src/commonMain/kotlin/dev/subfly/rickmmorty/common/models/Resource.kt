package dev.subfly.rickmmorty.common.models

sealed class Resource<out T> {
    data class Error(val message: String): Resource<Nothing>()
    data class Success<out T: Any>(val data: T): Resource<T>()
    object Loading: Resource<Nothing>()
}