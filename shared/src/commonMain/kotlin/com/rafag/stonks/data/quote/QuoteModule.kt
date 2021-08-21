package com.rafag.stonks.data.quote

import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.quote.internal.QuotePersistence
import com.rafag.stonks.data.quote.internal.QuoteRepositoryImpl
import com.stonks.db.StonksDatabase

class QuoteModule(
    private val httpClient: StonksHttpClient = StonksHttpClient(),
    val db: StonksDatabase,
) {

    fun repository(): QuoteRepository {
        return QuoteRepositoryImpl(
            httpClient = httpClient,
            persistence = QuotePersistence(db)
        )
    }
}