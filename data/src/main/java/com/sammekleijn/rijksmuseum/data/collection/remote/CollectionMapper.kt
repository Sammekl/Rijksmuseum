package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem

internal fun CollectionResponse.toCollectionItems(): List<CollectionItem> {
    val items = mutableListOf<CollectionItem>()
    artObjects.forEach { artObject ->
        if (items.none { it is CollectionItem.Header && it.author == artObject.principalOrFirstMaker }) {
            items.add(CollectionItem.Header(artObject.principalOrFirstMaker))
        }
        items.add(CollectionItem.Artwork(artObject.title, artObject.webImage?.url))
    }
    return items
}
