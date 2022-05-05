package com.sammekleijn.rijksmuseum.domain.result

private const val DEFAULT_LOADING_ITEMS_COUNT = 9

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Failure(val errorType: ErrorType) : ResultOf<Nothing>()
    data class Loading(val loadingItems: Int = DEFAULT_LOADING_ITEMS_COUNT) : ResultOf<Nothing>()
}
