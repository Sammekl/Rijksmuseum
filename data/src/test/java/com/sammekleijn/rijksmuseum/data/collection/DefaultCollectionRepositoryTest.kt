package com.sammekleijn.rijksmuseum.data.collection

import com.sammekleijn.rijksmuseum.data.collection.remote.CollectionDataSource
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class DefaultCollectionRepositoryTest {

    private val dataSource = mockk<CollectionDataSource>(relaxed = true)

    private val repository = DefaultCollectionRepository(dataSource)

    @Test
    fun `when getting collection items, fetch it from the data source`() {
        repository.getCollection()

        coVerify { dataSource.getCollection() }
    }
}
