package com.sammekleijn.rijksmuseum.data.collection.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import javax.inject.Inject

internal class CollectionPagingSource @Inject constructor(
    private val service: CollectionService,
) : PagingSource<Int, CollectionItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionItem> {
        val pageIndex = params.key ?: 1
        val response = service.getCollection(pageIndex)
        val items = if (response.isSuccessful) {
            response.body()?.toCollectionItems() ?: emptyList()
        } else emptyList()

        return LoadResult.Page(
            data = items,
            prevKey = null,
            nextKey = if (items.isEmpty()) null else pageIndex + (params.loadSize / PAGE_SIZE)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, CollectionItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
