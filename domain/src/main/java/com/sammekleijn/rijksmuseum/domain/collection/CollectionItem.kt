package com.sammekleijn.rijksmuseum.domain.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CollectionItem {
    data class Header(val author: String) : CollectionItem()

    data class Artwork(
        val title: String,
        val image: ArtImage?
    ) : CollectionItem() {

        @Parcelize
        data class ArtImage(
            val url: String,
            val width: Int,
            val height: Int
        ) : Parcelable
    }
}
