package com.sammekleijn.rijksmuseum.domain.result

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException

private const val DEFAULT_LOADING_ITEMS_COUNT = 9

sealed class ResultOf<out T> {
    data class Success<out R>(val value: R) : ResultOf<R>()
    data class Failure(val errorType: ErrorType) : ResultOf<Nothing>()
    data class Loading(val loadingItems: Int = DEFAULT_LOADING_ITEMS_COUNT) : ResultOf<Nothing>()
}

// Performs an action and maps it to the appropriate result
inline fun <reified T> mapResult(
    action: () -> T?,
): ResultOf<T> = mapResult(action, { (it as? List<*>)?.isEmpty() ?: false })

// Performs an action and maps it to the appropriate including empty check
@Suppress("TooGenericExceptionCaught")
inline fun <reified T> mapResult(
    action: () -> T?,
    emptyCheck: (T) -> Boolean
): ResultOf<T> {
    return try {
        val result = action()
        when {
            result == null -> ResultOf.Failure(ErrorType.Internal)
            emptyCheck(result) -> ResultOf.Failure(ErrorType.Empty)
            else -> ResultOf.Success(result)
        }
    } catch (e: Exception) {
        when (e) {
            is UnknownHostException,
            is TimeoutException,
            is SSLHandshakeException,
            is SocketTimeoutException -> ResultOf.Failure(ErrorType.Network())
            else -> throw e
        }
    }
}

suspend fun <F, T> ResultOf<List<F>>.transform(transformation: suspend (item: F) -> T): ResultOf<List<T>> =
    when (this) {
        is ResultOf.Success -> ResultOf.Success(value.map { transformation(it) })
        is ResultOf.Failure -> ResultOf.Failure(errorType)
        is ResultOf.Loading -> ResultOf.Loading(loadingItems)
    }

suspend fun <F, T> ResultOf<F>.transformSingle(transformation: suspend (item: F) -> T): ResultOf<T> =
    when (this) {
        is ResultOf.Success -> ResultOf.Success(transformation(value))
        is ResultOf.Failure -> ResultOf.Failure(errorType)
        is ResultOf.Loading -> ResultOf.Loading(loadingItems)
    }
