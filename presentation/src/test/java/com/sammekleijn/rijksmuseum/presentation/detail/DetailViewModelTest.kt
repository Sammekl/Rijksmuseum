package com.sammekleijn.rijksmuseum.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.sammekleijn.rijksmuseum.domain.common.StringResource
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionViewItem
import com.sammekleijn.rijksmuseum.test.helpers.InstantTaskExecutorExtension
import com.sammekleijn.rijksmuseum.test.helpers.testObserver
import io.github.glytching.junit.extension.random.Random
import io.github.glytching.junit.extension.random.RandomBeansExtension
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(
    RandomBeansExtension::class,
    InstantTaskExecutorExtension::class
)
internal class DetailViewModelTest {

    private val savedStateHandle = mockk<SavedStateHandle>(relaxed = true)

    @Test
    fun `when initialising, set the artwork from savedSate`(
        @Random item: CollectionViewItem.ArtworkView
    ) {
        coEvery { savedStateHandle.get<CollectionViewItem>("item") } returns item

        DetailViewModel(savedStateHandle).run {
            artwork.testObserver().assertValue(item)
        }
    }

    @Test
    fun `when initialising, set the productionPlaces as unknown if empty`(
        @Random item: CollectionViewItem.ArtworkView
    ) {
        val itemWithProductionPlaces = item.copy(content = item.content.copy(productionPlaces = emptyList()))
        coEvery { savedStateHandle.get<CollectionViewItem>("item") } returns itemWithProductionPlaces

        DetailViewModel(savedStateHandle).run {
            productionPlaces.testObserver().assertValue(StringResource.Id(R.string.unknown))
        }
    }

    @Test
    fun `when initialising, set the productionPlaces as distinct list`(
        @Random item: CollectionViewItem.ArtworkView
    ) {
        val productionPlacesWithDuplicates = item.content.productionPlaces.toMutableList()
        productionPlacesWithDuplicates += item.content.productionPlaces.random()
        val itemWithProductionPlaces = item.copy(content = item.content.copy(productionPlaces = productionPlacesWithDuplicates))

        coEvery { savedStateHandle.get<CollectionViewItem>("item") } returns itemWithProductionPlaces

        DetailViewModel(savedStateHandle).run {
            productionPlaces.testObserver().assertValue(StringResource.Value(item.content.productionPlaces.joinToString(", ")))
        }
    }
}
