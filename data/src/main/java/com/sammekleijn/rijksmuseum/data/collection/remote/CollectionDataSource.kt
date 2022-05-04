package com.sammekleijn.rijksmuseum.data.collection.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal const val PAGE_SIZE = 10

internal class CollectionDataSource @Inject constructor(
    private val pagingSource: CollectionPagingSource
) {

    fun getCollection(): Flow<PagingData<CollectionItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }
}
