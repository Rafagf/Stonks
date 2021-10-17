package com.rafag.stonks.internal.data.repositories.search

import com.rafag.stonks.internal.data.httpclient.HttpRequest
import com.rafag.stonks.internal.data.httpclient.Method.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

internal object SearchApi {

    fun searchRequest(symbol: String) = HttpRequest<ApiSearchResponse>(
        method = GET,
        url = "search?q=${symbol}"
    )
}

@Serializable
internal data class ApiSearchResponse(
    @SerialName("result") val result: List<ApiSearchItemResponse>,
)

@Serializable
internal data class ApiSearchItemResponse(
    @SerialName("description") val description: String,
    @SerialName("displaySymbol") val displaySymbol: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("type") val type: String,
)