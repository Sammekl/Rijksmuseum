package com.sammekleijn.rijksmuseum.data.collection

import com.sammekleijn.rijksmuseum.data.collection.remote.CollectionDataSource
import com.sammekleijn.rijksmuseum.data.collection.remote.toCollectionItems
import com.sammekleijn.rijksmuseum.data.common.asFlow
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import com.sammekleijn.rijksmuseum.domain.result.ResultOf
import com.sammekleijn.rijksmuseum.domain.result.transformSingle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

internal class DefaultCollectionRepository @Inject constructor(
    private val dataSource: CollectionDataSource
) : CollectionRepository {

    override suspend fun getCollection(): Flow<ResultOf<List<CollectionItem>>> = asFlow {
        dataSource.getCollection()
            .transformSingle { response ->
                response.toCollectionItems()
            }
    }.onStart { emit(ResultOf.Loading(9)) }
}
