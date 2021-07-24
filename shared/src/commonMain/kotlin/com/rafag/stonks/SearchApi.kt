package com.rafag.stonks

import com.rafag.stonks.Method.*
import io.ktor.http.HttpMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class SearchApi {

    fun searchRequest(symbol: String) = HttpRequest<ApiSearchResponse>(
        method = GET,
        url = "search?q=${symbol}"
    )
}

@Serializable
data class ApiSearchResponse(
    @SerialName("c") val current: Double,
    @SerialName("h") val high: Double,
    @SerialName("l") val low: Double,
    @SerialName("o") val open: Double,
    @SerialName("pc") val previousClose: Double,
)

{
    "count": 4,
    "result": [
    {
        "description": "APPLE INC",
        "displaySymbol": "AAPL",
        "symbol": "AAPL",
        "type": "Common Stock"
    },
    {
        "description": "APPLE INC",
        "displaySymbol": "AAPL.SW",
        "symbol": "AAPL.SW",
        "type": "Common Stock"
    },
    {
        "description": "APPLE INC",
        "displaySymbol": "APC.BE",
        "symbol": "APC.BE",
        "type": "Common Stock"
    },
    {
        "description": "APPLE INC",
        "displaySymbol": "APC.DE",
        "symbol": "APC.DE",
        "type": "Common Stock"
    }
    ]
}