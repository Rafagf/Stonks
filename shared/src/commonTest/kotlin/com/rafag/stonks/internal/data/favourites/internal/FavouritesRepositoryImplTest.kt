package com.rafag.stonks.internal.data.favourites.internal

import com.rafag.stonks.internal.data.favourites.FavouritesPersistence
import com.rafag.stonks.internal.data.favourites.FavouritesRepositoryImpl
import com.rafag.stonks.internal.data.favourites.FavouritesRepositoryImpl.*
import com.rafag.stonks.mock
import com.rafag.stonks.runBlocking
import com.rafag.stonks.whenever
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val A_SYMBOL = "AAPL"

class FavouritesRepositoryImplTest {

    private val persistence = mock<FavouritesPersistence>()

    private val repository = FavouritesRepositoryImpl(persistence)

    @Test
    fun `given success when getting all saved then return list`() {
        val expectedList = listOf(A_SYMBOL)
        whenever(persistence.getAll()).thenReturn(expectedList)

        runBlocking {
            val returnedList = repository.getAll()
            assertEquals(returnedList, expectedList)
        }
    }

    @Test
    fun `given error when getting all saved then throw exception`() {
        val throwable = RuntimeException("foo")
        whenever(persistence.getAll()).thenThrow(throwable)

        assertFailsWith<ErrorFetchingFavourites> {
            runBlocking { repository.getAll() }
        }
    }

    @Test
    fun `given success when saving then do nothing`() {
        runBlocking {
            repository.save(A_SYMBOL)
        }
    }

    @Test
    fun `given error when saving then throw exception`() {
        val throwable = RuntimeException("foo")
        whenever(persistence.save(A_SYMBOL)).thenThrow(throwable)

        assertFailsWith<ErrorSavingFavourite> {
            runBlocking { repository.save(A_SYMBOL) }
        }
    }

    @Test
    fun `given success when unsaving then do nothing`() {
        runBlocking { repository.unsave(A_SYMBOL) }
    }

    @Test
    fun `given error when unsaving then throw exception`() {
        val throwable = RuntimeException("foo")
        whenever(persistence.unsave(A_SYMBOL)).thenThrow(throwable)

        assertFailsWith<ErrorUnSaveFavourite> {
            runBlocking { repository.unsave(A_SYMBOL) }
        }
    }
}