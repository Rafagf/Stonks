package com.rafag.stonks.internal.domain.usecases

import com.rafag.stonks.domain.usecases.StonkSearch
import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import com.rafag.stonks.internal.domain.repositories.SearchRepository
import com.rafag.stonks.internal.fixtures.SearchFixture
import com.rafag.stonks.mock
import com.rafag.stonks.runBlocking
import com.rafag.stonks.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlin.test.Test
import kotlin.test.assertEquals

private const val FAVED_SYMBOL = "GOOG"
private const val FAVED_NAME = "google"
private const val UNFAVED_SYMBOL = "GOOGF"
private const val UNFAVED_NAME = "googles fake inc"
private const val A_SEARCH_QUERY = FAVED_SYMBOL
private val A_FAVED_SYMBOLS = listOf(FAVED_SYMBOL)
private val A_FAVED_SEARCH_ITEM = SearchFixture.aSearchItem(symbol = FAVED_SYMBOL, name = FAVED_NAME)
private val A_NOT_FAVED_SEARCH_ITEM = SearchFixture.aSearchItem(UNFAVED_SYMBOL, name = UNFAVED_NAME)
private val A_SEARCH_ITEMS = SearchFixture.aSearch(listOf(A_FAVED_SEARCH_ITEM, A_NOT_FAVED_SEARCH_ITEM))

class SearchStonksUseCaseImplTest {

    private val searchRepository = mock<SearchRepository>()
    private val favouritesRepository = mock<FavouritesRepository>()
    private val useCase = SearchStonksUseCaseImpl(searchRepository, favouritesRepository)

    @Test
    fun `given query when invoked then fetch all matching symbols and its faved state`() = runBlocking {
        whenever(searchRepository.search(A_SEARCH_QUERY)).thenReturn(A_SEARCH_ITEMS)
        whenever(favouritesRepository.getAll()).thenReturn(flowOf(A_FAVED_SYMBOLS))

        val expected = listOf(
            StonkSearch(
                symbol = FAVED_SYMBOL,
                name = FAVED_NAME,
                faved = true,
            ),
            StonkSearch(
                symbol = UNFAVED_SYMBOL,
                name = UNFAVED_NAME,
                faved = false,
            ),
        )

        val result = useCase.invoke(A_SEARCH_QUERY)

        assertEquals(result.first(), expected)
    }
}