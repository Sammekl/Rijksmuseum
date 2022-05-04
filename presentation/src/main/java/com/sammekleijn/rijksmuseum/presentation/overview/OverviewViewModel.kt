package com.sammekleijn.rijksmuseum.presentation.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
internal class OverviewViewModel @Inject constructor(
    private val repository: CollectionRepository
) : ViewModel() {

    val items = MutableLiveData<List<CollectionViewItem>>()

    fun getCollectionItems(): Flow<PagingData<CollectionViewItem>> =
        repository.getCollection().map { pagingData ->
            pagingData.map { it.toCollectionViewItem() }
        }.cachedIn(viewModelScope)

}
