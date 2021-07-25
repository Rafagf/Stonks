package com.rafag.stonks.api.internal

import com.rafag.stonks.api.internal.Method.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object QuoteApi {

    fun quoteRequest(symbol: String) = HttpRequest<ApiQuoteResponse>(
        method = GET,
        url = "quote?symbol=${symbol}"
    )
}

@Serializable
data class ApiQuoteResponse(
    @SerialName("c") val current: Double,
    @SerialName("h") val high: Double,
    @SerialName("l") val low: Double,
    @SerialName("o") val open: Double,
    @SerialName("pc") val previousClose: Double,
)