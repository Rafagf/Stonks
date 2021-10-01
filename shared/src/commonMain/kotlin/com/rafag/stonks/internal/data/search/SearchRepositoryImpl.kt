package com.rafag.stonks.internal.data.search

import com.rafag.stonks.internal.api.StonksHttpClient
import com.rafag.stonks.internal.api.ApiSearchItemResponse
import com.rafag.stonks.internal.api.ApiSearchResponse
import com.rafag.stonks.internal.api.SearchApi
import com.rafag.stonks.domain.repositories.Search
import com.rafag.stonks.domain.repositories.SearchItem
import com.rafag.stonks.domain.repositories.SearchRepository

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
