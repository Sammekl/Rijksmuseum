package com.sammekleijn.rijksmuseum.presentation.overview

import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem

internal fun CollectionItem.toCollectionViewItem() = CollectionViewItem(
    author = author,
    title = title,
    imageUrl = imageUrl
)

