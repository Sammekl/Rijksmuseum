package com.sammekleijn.rijksmuseum.domain.collection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class CollectionItem {
    data class Header(val author: String) : CollectionItem()

    data class Artwork(
        val title: String,
        val image: Image?,
        val content: Content
    ) : CollectionItem() {

        @Parcelize
        data class Image(
            val url: String,
            val width: Int,
            val height: Int
        ) : Parcelable

        @Parcelize
        data class Content(
            val longTitle: String,
            val objectNumber: String,
            val productionPlaces: List<String>
        ) : Parcelable
    }
}
