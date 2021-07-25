package com.rafag.stonks.data.quote

import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.quote.internal.QuoteRepositoryImpl

class QuoteModule(
    private val httpClient: StonksHttpClient
) {

    fun repository(): QuoteRepository {
        return QuoteRepositoryImpl(httpClient)
    }
}