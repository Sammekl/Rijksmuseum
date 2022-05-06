package com.sammekleijn.rijksmuseum.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sammekleijn.rijksmuseum.domain.common.StringResource
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val artwork = MutableLiveData<CollectionViewItem.ArtworkView>()
    val productionPlaces = MutableLiveData<StringResource>()

    init {
        savedStateHandle.get<CollectionViewItem.ArtworkView>("item")?.also {
            artwork.value = it
            productionPlaces.value = it.content.productionPlaces.toStringResource()
        } ?: throw IllegalStateException("No item provided!")
    }

    private fun List<String>.toStringResource(): StringResource =
        if (isEmpty()) StringResource.Id(R.string.unknown)
        else StringResource.Value(distinct().joinToString(", "))
}

