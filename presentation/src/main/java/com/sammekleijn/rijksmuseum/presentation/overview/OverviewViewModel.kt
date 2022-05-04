package com.sammekleijn.rijksmuseum.presentation.overview

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import com.sammekleijn.rijksmuseum.presentation.common.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class OverviewViewModel @Inject constructor(
    private val repository: CollectionRepository
) : ViewModel() {

    val onOpenArtWork = SingleLiveEvent<Pair<CollectionViewItem.Artwork, ImageView>>()
    val artworks = MutableStateFlow<PagingData<CollectionViewItem>>(PagingData.empty())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCollection().map { pagingData ->
                pagingData.map { it.toCollectionViewItem() }
            }.cachedIn(viewModelScope).collectLatest {
                artworks.value = it
            }
        }
    }

    fun onArtworkClicked(item: CollectionViewItem.Artwork, imageView: ImageView) {
        Timber.i("Opening art piece ${item.title}")
        onOpenArtWork.value = Pair(item, imageView)
    }
}
