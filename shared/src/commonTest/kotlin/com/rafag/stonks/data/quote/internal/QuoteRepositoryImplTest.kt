package com.rafag.stonks.data.quote.internal

import com.rafag.stonks.api.internal.QuoteApi
import com.rafag.stonks.fixtures.QuoteFixture
import com.rafag.stonks.mock
import com.rafag.stonks.mockHttp
import com.rafag.stonks.runBlocking
import com.rafag.stonks.verify
import com.rafag.stonks.whenever
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val A_SYMBOL = "AAPL"

private val AN_API_QUOTE = QuoteFixture.anApiQuote()
private val A_DB_QUOTE = QuoteFixture.aDbQuote(A_SYMBOL)
private val A_QUOTE = QuoteFixture.aQuote(A_SYMBOL)

class QuoteRepositoryImplTest {

    private val httpClient = mockHttp()
    private val persistence = mock<QuotePersistence>()

    private val repository = QuoteRepositoryImpl(httpClient.instance, persistence)

    @Test
    fun `given api success when fetching quote then return data and update persistence`() {
        val request = QuoteApi.quoteRequest(A_SYMBOL)
        httpClient.addHandler(request, AN_API_QUOTE)

        val result = runBlocking { repository.quote(A_SYMBOL) }

        verify(persistence).upsert(A_SYMBOL, AN_API_QUOTE)
        assertEquals(result, A_QUOTE)
    }

    @Test
    fun `given api error but db success when fetching quote then return data`() {
        val request = QuoteApi.quoteRequest(A_SYMBOL)
        val apiResponse = RuntimeException("Error")
        httpClient.addHandler(request, apiResponse)
        whenever(persistence.get(A_SYMBOL)).thenReturn(A_DB_QUOTE)

        val result = runBlocking { repository.quote(A_SYMBOL) }

        assertEquals(result, A_QUOTE)
    }

    @Test
    fun `given api and db error when fetching quote then throw exception`() {
        val request = QuoteApi.quoteRequest(A_SYMBOL)
        val apiResponse = RuntimeException("Error api")
        val dbResponse = RuntimeException("Error db")
        httpClient.addHandler(request, apiResponse)
        whenever(persistence.get(A_SYMBOL)).thenThrow(dbResponse)

        assertFailsWith<ErrorFetchingQuote> {
            runBlocking { repository.quote(A_SYMBOL) }
        }
    }
}