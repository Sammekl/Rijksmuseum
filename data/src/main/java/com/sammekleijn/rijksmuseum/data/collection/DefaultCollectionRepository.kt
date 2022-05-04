package com.sammekleijn.rijksmuseum.data.collection

import androidx.paging.PagingData
import com.sammekleijn.rijksmuseum.data.collection.remote.CollectionDataSource
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultCollectionRepository @Inject constructor(
    private val dataSource: CollectionDataSource
) : CollectionRepository {

    override fun getCollection(): Flow<PagingData<CollectionItem>> = dataSource.getCollection()
}
