package com.sammekleijn.rijksmuseum.domain.collection

sealed class CollectionItem {
    data class Header(val author: String) : CollectionItem()
    data class Artwork(
        val title: String,
        val imageUrl: String?
    ) : CollectionItem()
}
