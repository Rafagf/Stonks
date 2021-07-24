package com.rafag.stonks.data.quote

import com.rafag.stonks.api.ApiQuoteResponse

interface QuoteRepository {

    suspend fun quote(symbol: String): ApiQuoteResponse
}