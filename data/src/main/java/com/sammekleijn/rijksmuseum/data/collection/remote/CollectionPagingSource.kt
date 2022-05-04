package com.sammekleijn.rijksmuseum.data.collection.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sammekleijn.rijksmuseum.data.common.mapResult
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.domain.result.ResultOf
import com.sammekleijn.rijksmuseum.domain.result.toLoadResultException
import javax.inject.Inject

internal class CollectionPagingSource @Inject constructor(
    private val service: CollectionService,
) : PagingSource<Int, CollectionItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionItem> {
        val pageIndex = params.key ?: 1

        return when (val response = mapResult { service.getCollection(pageIndex) }) {
            is ResultOf.Success -> {
                val items = response.value.toCollectionItems()
                LoadResult.Page(
                    data = items,
                    prevKey = null,
                    nextKey = if (items.isEmpty()) null else pageIndex + (params.loadSize / PAGE_SIZE)
                )
            }
            is ResultOf.Failure -> LoadResult.Error(response.errorType.toLoadResultException())
            is ResultOf.Loading -> throw IllegalStateException("Response should not be of type loading!")
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CollectionItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
