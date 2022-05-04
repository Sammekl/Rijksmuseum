package com.sammekleijn.rijksmuseum.presentation.overview

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem

internal fun CollectionItem.toCollectionViewItem() = when (this) {
    is CollectionItem.Header -> toHeaderView()
    is CollectionItem.Artwork -> toArtworkView()
}

private fun CollectionItem.Header.toHeaderView() = CollectionViewItem.HeaderView(author)

private fun CollectionItem.Artwork.toArtworkView() = CollectionViewItem.ArtworkView(
    title = title,
    image = image?.let {
        CollectionViewItem.ArtworkView.Image(
            url = it.url,
            width = it.width,
            height = it.height
        )
    },
    content = CollectionViewItem.ArtworkView.Content(
        longTitle = content.longTitle,
        objectNumber = content.objectNumber,
        productionPlaces = content.productionPlaces
    )
)


