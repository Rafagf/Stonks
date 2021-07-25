package com.rafag.stonks.data.quote.internal

import com.rafag.stonks.api.ApiQuoteResponse
import com.rafag.stonks.api.QuoteApi
import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.quote.QuoteRepository
import com.stonks.db.QuoteDb
import com.stonks.db.QuoteQueries
import com.stonks.db.StonksDatabase

class QuoteRepositoryImpl(
    private val httpClient: StonksHttpClient,
    private val db: StonksDatabase,
) : QuoteRepository {

    override suspend fun quote(symbol: String): ApiQuoteResponse {
        //todo we need another layer of models: the one we return to clients, API, and DB
        return try {
            fromApi(symbol)
        } catch (exception: Exception) {
            fromDb(symbol)
        }
    }

    private suspend fun fromApi(symbol: String): ApiQuoteResponse {
        val apiResponse = httpClient.execute(QuoteApi.quoteRequest(symbol))
        db.quoteQueries.upsert(symbol, apiResponse)
        return apiResponse
    }

    private fun fromDb(symbol: String): ApiQuoteResponse {
        return db.quoteQueries.get(symbol).executeAsOne().toApi()
    }
}

private fun QuoteQueries.upsert(symbol: String, apiResponse: ApiQuoteResponse) {
    this.upsert(
        symbol = symbol,
        current = apiResponse.current,
        high = apiResponse.high,
        low = apiResponse.low,
        open_ = apiResponse.open,
        previousClose = apiResponse.previousClose
    )
}

private fun QuoteDb.toApi() = ApiQuoteResponse(
    current = current,
    high = high,
    low = low,
    open = open_,
    previousClose = previousClose
)
