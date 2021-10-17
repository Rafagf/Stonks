package com.rafag.stonks.internal.data.repositories.quote

import com.stonks.db.DbQuote
import com.stonks.db.StonksDatabase

internal class QuotePersistence(
    private val db: StonksDatabase,
) {

    fun upsert(symbol: String, apiResponse: ApiQuoteResponse) {
        db.dbQuoteQueries.upsert(
            symbol = symbol,
            current = apiResponse.current,
            high = apiResponse.high,
            low = apiResponse.low,
            open_ = apiResponse.open,
            previousClose = apiResponse.previousClose
        )
    }

    fun get(symbol: String): DbQuote {
        return db.dbQuoteQueries.get(symbol).executeAsOne()
    }
}