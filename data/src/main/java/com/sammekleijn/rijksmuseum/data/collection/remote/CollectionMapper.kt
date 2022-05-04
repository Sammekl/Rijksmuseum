package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem.Artwork
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem.Header

internal fun CollectionResponse.toCollectionItems(): List<CollectionItem> {
    val items = mutableListOf<CollectionItem>()
    artObjects.forEach { artObject ->
        if (items.none { it is Header && it.author == artObject.principalOrFirstMaker }) {
            items.add(artObject.toHeader())
        }
        items.add(artObject.toArtwork())
    }
    return items
}

private fun ArtObjectResponse.toHeader() = Header(principalOrFirstMaker)

private fun ArtObjectResponse.toArtwork() = Artwork(
    title = title,
    imageUrl = webImage?.url,
    content = Artwork.Content(
        longTitle = longTitle,
        objectNumber = objectNumber,
        productionPlaces = productionPlaces
    )
)
