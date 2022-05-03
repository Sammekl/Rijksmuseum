package com.sammekleijn.rijksmuseum.domain.result

import androidx.annotation.Keep

@Keep
sealed class ErrorType {
    data class Network(
        val type: Type = Type.CONNECTION
    ) : ErrorType() {

        enum class Type {
            CLIENT, SERVER, AUTH, CONNECTION, UNKNOWN
        }
    }

    object Internal : ErrorType()
    object Empty : ErrorType()
}

