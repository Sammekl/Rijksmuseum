package com.sammekleijn.rijksmuseum.presentation.common

import android.content.Context
import com.sammekleijn.rijksmuseum.domain.result.ErrorType
import com.sammekleijn.rijksmuseum.presentation.R

internal fun ErrorType.asErrorResource(context: Context) = when (this) {
    is ErrorType.Network -> when (type) {
        ErrorType.Network.Type.CLIENT,
        ErrorType.Network.Type.SERVER,
        ErrorType.Network.Type.AUTH -> context.getString(R.string.server_error_message)
        ErrorType.Network.Type.CONNECTION,
        ErrorType.Network.Type.UNKNOWN -> context.getString(R.string.network_error_message)
    }
    ErrorType.Internal -> context.getString(R.string.internal_error_message)
    ErrorType.Empty -> context.getString(R.string.no_data_message)
}
