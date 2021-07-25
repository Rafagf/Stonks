package com.rafag.stonks.data.search.internal

import com.rafag.stonks.api.internal.ApiSearchResponse
import com.rafag.stonks.api.internal.SearchApi
import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.search.SearchRepository

class SearchRepositoryImpl(
    private val httpClient: StonksHttpClient
) : SearchRepository {

    override suspend fun searchRequest(symbol: String): ApiSearchResponse {
        return httpClient.execute(SearchApi.searchRequest(symbol))
    }
}