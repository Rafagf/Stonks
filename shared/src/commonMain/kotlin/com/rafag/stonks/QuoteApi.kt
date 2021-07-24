package com.rafag.stonks

import com.rafag.stonks.Method.*
import io.ktor.http.HttpMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class QuoteApi {

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