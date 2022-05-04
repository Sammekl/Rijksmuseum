package com.sammekleijn.rijksmuseum.presentation.detail

import androidx.lifecycle.SavedStateHandle
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionViewItem
import io.github.glytching.junit.extension.random.Random
import io.github.glytching.junit.extension.random.RandomBeansExtension
import io.mockk.coEvery
import io.mockk.mockk
import nl.dpgmedia.donaldduck.test.helpers.InstantTaskExecutorExtension
import nl.dpgmedia.donaldduck.test.helpers.testObserver
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
}