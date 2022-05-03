package com.sammekleijn.rijksmuseum.data.common

import com.sammekleijn.rijksmuseum.data.common.StatusCodes.AUTH_ERRORS
import com.sammekleijn.rijksmuseum.data.common.StatusCodes.BAD_REQUEST
import com.sammekleijn.rijksmuseum.data.common.StatusCodes.CLIENT_ERRORS
import com.sammekleijn.rijksmuseum.data.common.StatusCodes.SERVER_ERRORS
import com.sammekleijn.rijksmuseum.domain.result.ErrorType
import com.sammekleijn.rijksmuseum.domain.result.ErrorType.Network.Type.AUTH
import com.sammekleijn.rijksmuseum.domain.result.ErrorType.Network.Type.CLIENT
import com.sammekleijn.rijksmuseum.domain.result.ErrorType.Network.Type.CONNECTION
import com.sammekleijn.rijksmuseum.domain.result.ErrorType.Network.Type.SERVER
import com.sammekleijn.rijksmuseum.domain.result.ErrorType.Network.Type.UNKNOWN
import com.sammekleijn.rijksmuseum.domain.result.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeoutException

@Suppress("TooGenericExceptionCaught")
suspend inline fun <reified T> mapResult(
    crossinline networkCall: suspend () -> Response<T>,
): ResultOf<T> = withContext(Dispatchers.IO) {
    try {
        networkCall().mapResult()
    } catch (e: Exception) {
        Timber.w(e)
        e.mapResult()
    }
}

fun <T> Response<T>.mapResult(): ResultOf<T> {
    return when {
        isSuccessful -> {
            val body = body()
            if (body == null) ResultOf.Failure(ErrorType.Empty)
            else ResultOf.Success(body)
        }
        else -> ResultOf.Failure(code().toNetworkError())
    }
}

fun Exception.mapResult(): ResultOf.Failure {
    val errorType = when (this) {
        is TimeoutException -> ErrorType.Network(CONNECTION)
        is IOException -> ErrorType.Network(CONNECTION)
        is HttpException -> code().toNetworkError()
        else -> ErrorType.Internal
    }
    return ResultOf.Failure(errorType)
}

private fun Int.toNetworkError() = when (this) {
    in AUTH_ERRORS -> ErrorType.Network(AUTH)
    in CLIENT_ERRORS, BAD_REQUEST -> ErrorType.Network(CLIENT)
    in SERVER_ERRORS -> ErrorType.Network(SERVER)
    else -> ErrorType.Network(UNKNOWN)
}

@Suppress("MagicNumber")
private object StatusCodes {
    val AUTH_ERRORS = 401..403
    val CLIENT_ERRORS = 404..451
    val SERVER_ERRORS = 500..511
    const val BAD_REQUEST = 400
}
