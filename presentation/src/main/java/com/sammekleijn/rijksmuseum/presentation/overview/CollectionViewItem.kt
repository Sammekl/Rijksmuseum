package com.sammekleijn.rijksmuseum.presentation.overview

sealed class CollectionViewItem {
    data class Header(val author: String) : CollectionViewItem()
    data class Artwork(
        val title: String,
        val imageUrl: String?
    ) : CollectionViewItem()
}
