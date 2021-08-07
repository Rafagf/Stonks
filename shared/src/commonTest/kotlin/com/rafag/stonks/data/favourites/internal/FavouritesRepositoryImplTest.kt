package com.rafag.stonks.data.favourites.internal

import com.rafag.stonks.data.favourites.internal.FavouritesRepositoryImpl.*
import com.rafag.stonks.mock
import com.rafag.stonks.runBlocking
import com.rafag.stonks.whenever
import kotlin.test.Test
import kotlin.test.assertFailsWith

private const val A_SYMBOL = "AAPL"

class FavouritesRepositoryImplTest {

    private val persistence = mock<FavouritesPersistence>()

    private val repository = FavouritesRepositoryImpl(persistence)

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

        assertFailsWith<CannotSaveFavourite> {
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

        assertFailsWith<CannotUnSaveFavourite> {
            repository.unsave(A_SYMBOL)
        }
    }
}