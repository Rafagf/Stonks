package com.rafag.stonks.data.quote.internal

import com.rafag.stonks.api.internal.ApiQuoteResponse
import com.stonks.db.DbQuote
import com.stonks.db.StonksDatabase

class QuotePersistence(
    private val db: StonksDatabase,
) {

    fun upsert(symbol: String, apiResponse: ApiQuoteResponse) {
        db.quoteQueries.upsert(
            symbol = symbol,
            current = apiResponse.current,
            high = apiResponse.high,
            low = apiResponse.low,
            open_ = apiResponse.open,
            previousClose = apiResponse.previousClose
        )
    }

    fun get(symbol: String): DbQuote {
        return db.quoteQueries.get(symbol).executeAsOne()
    }
}