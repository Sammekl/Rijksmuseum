package com.sammekleijn.rijksmuseum.data.collection.remote

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.Error
import androidx.paging.PagingSource.LoadResult.Page
import com.sammekleijn.rijksmuseum.domain.result.ErrorType
import com.sammekleijn.rijksmuseum.domain.result.LoadResultException
import io.github.glytching.junit.extension.random.Random
import io.github.glytching.junit.extension.random.RandomBeansExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import retrofit2.Response

@ExtendWith(
    RandomBeansExtension::class
)
internal class CollectionPagingSourceTest {

    private val service = mockk<CollectionService>(relaxed = true)

    private val pagingSource = CollectionPagingSource(service)

    @Test
    fun `when loading, call the service with the page index`(
        @Random pageKey: Int
    ) = runTest {
        val params = mockk<PagingSource.LoadParams<Int>>(relaxed = true) {
            every { key } returns pageKey
        }

        pagingSource.load(params)

        coVerify { service.getCollection(pageKey) }
    }

    @Test
    fun `when success, return the result`(
        @Random response: CollectionResponse
    ) = runTest {
        val params = mockk<PagingSource.LoadParams<Int>>(relaxed = true) {
            every { key } returns 1
        }
        coEvery { service.getCollection(1) } returns Response.success(response)

        val result = pagingSource.load(params)

        assertTrue(result is Page)
        assertEquals(response.toCollectionItems(), (result as Page).data)
    }

    @Test
    fun `when success but result is empty, return the result`() = runTest {
        val params = mockk<PagingSource.LoadParams<Int>>(relaxed = true) {
            every { key } returns 1
        }
        coEvery { service.getCollection(1) } returns Response.success(CollectionResponse(emptyList()))

        val result = pagingSource.load(params)

        assertTrue(result is Error)
        assertEquals(ErrorType.Empty, ((result as Error).throwable as LoadResultException).errorType)
    }

    @Test
    fun `when 500 error, return server error`() = runTest {
        val params = mockk<PagingSource.LoadParams<Int>>(relaxed = true) {
            every { key } returns 1
        }
        coEvery { service.getCollection(1) } returns Response.error(500, "Woopsie!".toResponseBody())

        val result = pagingSource.load(params)

        assertTrue(result is Error)
        assertEquals(ErrorType.Network(ErrorType.Network.Type.SERVER), ((result as Error).throwable as LoadResultException).errorType)
    }

    @Test
    fun `when authentication error, return auth error`() = runTest {
        val params = mockk<PagingSource.LoadParams<Int>>(relaxed = true) {
            every { key } returns 1
        }
        coEvery { service.getCollection(1) } returns Response.error(401, "Woopsie!".toResponseBody())

        val result = pagingSource.load(params)

        assertTrue(result is Error)
        assertEquals(ErrorType.Network(ErrorType.Network.Type.AUTH), ((result as Error).throwable as LoadResultException).errorType)
    }
}
