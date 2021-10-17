package com.rafag.stonks.internal.domain.usecases

import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import com.rafag.stonks.internal.fixtures.QuoteFixture
import com.rafag.stonks.internal.data.repositories.quote.QuoteRepositoryImpl
import com.rafag.stonks.mock
import com.rafag.stonks.runBlocking
import com.rafag.stonks.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlin.test.Test
import kotlin.test.assertEquals

private const val A_SYMBOL = "GOOG"
private val A_FAVED_SYMBOLS = listOf("GOOG")
private val A_QUOTE = QuoteFixture.aQuote(A_SYMBOL)

class FetchFavedQuotesUseCaseImplTest {

    private val favouritesRepository = mock<FavouritesRepository>()
    private val quoteRepository = mock<QuoteRepositoryImpl>()

    private val useCase = FetchFavedQuotesUseCaseImpl(quoteRepository, favouritesRepository)

    @Test
    fun `when invoked then fetch faved symbols and its quotes`() = runBlocking {
        whenever(favouritesRepository.getAll()).thenReturn(flowOf(A_FAVED_SYMBOLS))
        whenever(quoteRepository.quote(A_SYMBOL)).thenReturn(A_QUOTE)

        val result = useCase.invoke()

        assertEquals(result.first(), listOf(A_QUOTE.toFavedQuote()))
    }

}