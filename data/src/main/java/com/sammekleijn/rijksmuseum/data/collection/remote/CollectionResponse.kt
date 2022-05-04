package com.sammekleijn.rijksmuseum.data.collection.remote

import androidx.annotation.Keep

@Keep
data class CollectionResponse(
    val artObjects: List<ArtObjectResponse>,
)

@Keep
data class ArtObjectResponse(
    val title: String,
    val principalOrFirstMaker: String,
    val webImage: WebImageResponse
)

@Keep
data class WebImageResponse(
    val url: String?
)
