package com.sammekleijn.rijksmuseum.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val artwork = MutableLiveData<CollectionViewItem.ArtworkView>()

    init {
        savedStateHandle.get<CollectionViewItem.ArtworkView>("item").also {
            artwork.value = it
        } ?: throw IllegalStateException("No item provided!")
    }

}
