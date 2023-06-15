package dev.subfly.rickmmorty.common.utils

sealed class NetworkErrors(
    override val message: String
) : Exception(message) {
    object HTTPException : NetworkErrors(message = "Could not get the data from server, please try again later...")
    object NetworkException : NetworkErrors(message = "A network error happened, check your internet connection and try again later...")
    object UnknownError : NetworkErrors(message = "An unexpected error happened, please try again later...")
}