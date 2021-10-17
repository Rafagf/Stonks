package com.rafag.stonks.internal.data.repositories.quote

import com.rafag.stonks.internal.data.httpclient.HttpRequest
import com.rafag.stonks.internal.data.httpclient.Method.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object QuoteApi {

    fun quoteRequest(symbol: String) = HttpRequest<ApiQuoteResponse>(
        method = GET,
        url = "quote?symbol=${symbol}"
    )
}

@Serializable
internal data class ApiQuoteResponse(
    @SerialName("c") val current: Double,
    @SerialName("h") val high: Double,
    @SerialName("l") val low: Double,
    @SerialName("o") val open: Double,
    @SerialName("pc") val previousClose: Double,
)