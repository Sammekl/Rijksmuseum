package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.data.BuildConfig
import retrofit2.Response
import retrofit2.http.GET

internal interface CollectionService {

    @GET("/api/nl/collection?key=${BuildConfig.RIJKSMUSEUM_API_KEY}")
    suspend fun getCollection(): Response<CollectionResponse>
}
