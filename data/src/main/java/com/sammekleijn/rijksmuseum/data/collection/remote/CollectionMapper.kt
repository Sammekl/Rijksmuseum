package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem.Artwork
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem.Artwork.ArtImage
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem.Header

internal fun CollectionResponse.toCollectionItems(): List<CollectionItem> {
    val items = mutableListOf<CollectionItem>()
    artObjects.forEach { artObject ->
        if (items.none { it is Header && it.author == artObject.principalOrFirstMaker }) {
            items.add(Header(artObject.principalOrFirstMaker))
        }
        items.add(Artwork(artObject.title, artObject.webImage?.let { ArtImage(it.url, it.width, it.height) }))
    }
    return items
}
