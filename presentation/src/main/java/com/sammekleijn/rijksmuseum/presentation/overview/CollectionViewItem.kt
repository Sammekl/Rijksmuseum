package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
sealed class CollectionViewItem : Parcelable {
    @Parcelize
    data class HeaderView(val author: String) : CollectionViewItem()

    @Parcelize
    data class ArtworkView(
        val title: String,
        val image: Image?,
        val content: Content
    ) : CollectionViewItem() {

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
