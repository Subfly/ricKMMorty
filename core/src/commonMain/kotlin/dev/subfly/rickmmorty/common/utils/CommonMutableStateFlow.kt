package dev.subfly.rickmmorty.common.utils

import kotlinx.coroutines.flow.MutableStateFlow


expect open class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>) : MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow() = CommonMutableStateFlow(this)