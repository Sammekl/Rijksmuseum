package com.sammekleijn.rijksmuseum.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> asFlow(something: suspend () -> T): Flow<T> = flow {
    emit(something())
}
