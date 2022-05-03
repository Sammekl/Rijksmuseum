package com.sammekleijn.rijksmuseum.data.networking

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

internal class ApiTokenAuthenticator @Inject constructor(
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        return response.request.newBuilder()
            .build()
    }
}
