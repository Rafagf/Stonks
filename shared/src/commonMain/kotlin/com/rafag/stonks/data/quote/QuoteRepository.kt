package com.rafag.stonks.data.quote

interface QuoteRepository {

    suspend fun quote(symbol: String): Quote
}

data class Quote(
    val symbol: String,
    val current: Double,
    val high: Double,
    val low: Double,
    val open: Double,
    val previousClose: Double,
)
