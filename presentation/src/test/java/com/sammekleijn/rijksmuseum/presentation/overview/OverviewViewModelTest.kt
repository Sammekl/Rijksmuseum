package com.sammekleijn.rijksmuseum.presentation.overview

import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class OverviewViewModelTest {
    private val repository = mockk<CollectionRepository>(relaxed = true)

    @Test
    fun `when initialised, it fetches the data from repository`() {
        OverviewViewModel(repository)

        coVerify { repository.getCollection() }
    }
}
