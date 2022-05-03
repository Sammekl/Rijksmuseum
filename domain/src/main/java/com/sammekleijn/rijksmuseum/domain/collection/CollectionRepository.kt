package com.sammekleijn.rijksmuseum.domain.collection

import com.sammekleijn.rijksmuseum.domain.result.ResultOf
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {

    suspend fun getCollection(): Flow<ResultOf<List<CollectionItem>>>
}
