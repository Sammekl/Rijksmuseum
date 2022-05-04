package com.sammekleijn.rijksmuseum.domain.collection

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {

    fun getCollection(): Flow<PagingData<CollectionItem>>
}
