package com.sammekleijn.rijksmuseum.presentation.overview

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem

internal fun CollectionItem.toCollectionViewItem() = when (this) {
    is CollectionItem.Header -> CollectionViewItem.Header(author)
    is CollectionItem.Artwork -> CollectionViewItem.Artwork(title, image?.let { CollectionViewItem.Artwork.ArtImage(it.url, it.width, it.height) })
}

