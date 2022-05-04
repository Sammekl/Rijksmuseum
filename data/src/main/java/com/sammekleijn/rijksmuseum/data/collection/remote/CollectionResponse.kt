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
    val longTitle: String,
    val objectNumber: String,
    val webImage: WebImageResponse?,
    val productionPlaces: List<String>
)

@Keep
data class WebImageResponse(
    val url: String
)
