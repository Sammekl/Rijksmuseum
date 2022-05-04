package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.data.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface CollectionService {

    @GET("/api/nl/collection?key=${BuildConfig.RIJKSMUSEUM_API_KEY}&ps=10&s=artist&imgonly=true")
    suspend fun getCollection(
        @Query("p") page: Int
    ): Response<CollectionResponse>
}
