package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem

internal fun CollectionResponse.toCollectionItems(): List<CollectionItem> = artObjects.map {
    CollectionItem(
        author = it.principalOrFirstMaker,
        title = it.title,
        imageUrl = it.webImage?.url
    )
}
