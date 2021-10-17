package com.rafag.stonks.fixtures

import com.rafag.stonks.internal.data.repositories.quote.ApiQuoteResponse
import com.rafag.stonks.domain.repositories.Quote
import com.stonks.db.DbQuote

internal object QuoteFixture {

    fun anApiQuote() = ApiQuoteResponse(
        current = 3.0,
        high = 4.0,
        low = 2.0,
        open = 2.3,
        previousClose = 3.4
    )

    fun aDbQuote(symbol: String) = DbQuote(
        symbol = symbol,
        current = 3.0,
        high = 4.0,
        low = 2.0,
        open_ = 2.3,
        previousClose = 3.4
    )

    fun aQuote(symbol: String) = Quote(
        symbol = symbol,
        current = 3.0,
        high = 4.0,
        low = 2.0,
        open = 2.3,
        previousClose = 3.4
    )
}
