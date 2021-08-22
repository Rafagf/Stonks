package com.rafag.stonks.data.search.internal

import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.api.internal.ApiSearchItemResponse
import com.rafag.stonks.api.internal.ApiSearchResponse
import com.rafag.stonks.api.internal.SearchApi
import com.rafag.stonks.data.search.Search
import com.rafag.stonks.data.search.SearchItem
import com.rafag.stonks.data.search.SearchRepository

internal class SearchRepositoryImpl(
    private val httpClient: StonksHttpClient
) : SearchRepository {

    override suspend fun searchRequest(symbol: String): Search {
        return httpClient.execute(SearchApi.searchRequest(symbol)).toModel()
    }
}

private fun ApiSearchResponse.toModel() = Search(
    list = result.map { it.toModel() }
)

private fun ApiSearchItemResponse.toModel() = SearchItem(
    name = description,
    displaySymbol = displaySymbol,
    symbol = symbol,
    type = type
)
