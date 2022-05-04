package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
sealed class CollectionViewItem : Parcelable {
    @Parcelize
    data class Header(val author: String) : CollectionViewItem()

    @Parcelize
    data class Artwork(
        val title: String,
        val image: ArtImage?
    ) : CollectionViewItem() {
        @Parcelize
        data class ArtImage(
            val url: String, val width: Int, val height: Int
        ) : Parcelable
    }
}
