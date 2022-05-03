package com.sammekleijn.rijksmuseum.data.collection.remote

import com.sammekleijn.rijksmuseum.data.common.mapResult
import com.sammekleijn.rijksmuseum.domain.result.ResultOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class CollectionDataSource @Inject constructor(
    private val service: CollectionService
) {

    suspend fun getCollection(): ResultOf<CollectionResponse> = withContext(Dispatchers.IO) {
        mapResult {
            service.getCollection()
        }
    }
}
