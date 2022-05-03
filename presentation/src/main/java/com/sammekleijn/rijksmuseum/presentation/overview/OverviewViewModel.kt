package com.sammekleijn.rijksmuseum.presentation.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import com.sammekleijn.rijksmuseum.domain.result.ResultOf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class OverviewViewModel @Inject constructor(
    private val repository: CollectionRepository
) : ViewModel() {

    val items = MutableLiveData<List<CollectionItem>>()

    init {
        viewModelScope.launch {
            repository.getCollection()
                .collect {
                    when (it) {
                        is ResultOf.Success -> items.postValue(it.value)
                    }
                }
        }
    }
}
