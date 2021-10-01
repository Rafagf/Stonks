package com.rafag.stonks.internal.data.quote

import com.rafag.stonks.internal.api.StonksHttpClient
import com.rafag.stonks.internal.api.ApiQuoteResponse
import com.rafag.stonks.internal.api.QuoteApi
import com.rafag.stonks.domain.repositories.Quote
import com.rafag.stonks.domain.repositories.QuoteRepository
import com.stonks.db.DbQuote

internal class QuoteRepositoryImpl(
    private val httpClient: StonksHttpClient,
    private val persistence: QuotePersistence,
) : QuoteRepository {

    override suspend fun quote(symbol: String): Quote {
        return try {
            fromApi(symbol)
        } catch (exception: Exception) {
            fromDb(symbol)
        }
    }

    private suspend fun fromApi(symbol: String): Quote {
        val apiResponse = httpClient.execute(QuoteApi.quoteRequest(symbol))
        persistence.upsert(symbol, apiResponse)
        return apiResponse.toModel(symbol)
    }

    private fun fromDb(symbol: String): Quote {
        return try {
            persistence.get(symbol).toModel()
        } catch (exception: Exception) {
            throw ErrorFetchingQuote(symbol, exception)
        }
    }
}

data class ErrorFetchingQuote(val symbol: String, override val cause: Throwable) : Throwable("Could not fetch favourites -  $cause")

private fun ApiQuoteResponse.toModel(symbol: String) = Quote(
    symbol = symbol,
    current = current,
    high = high,
    low = low,
    open = open,
    previousClose = previousClose
)

private fun DbQuote.toModel() = Quote(
    symbol = symbol,
    current = current,
    high = high,
    low = low,
    open = open_,
    previousClose = previousClose
)
