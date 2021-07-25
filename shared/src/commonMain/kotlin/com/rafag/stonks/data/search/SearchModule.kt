package com.rafag.stonks.data.search

import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.search.internal.SearchRepositoryImpl

class SearchModule(
    private val httpClient: StonksHttpClient
) {

    fun repository(): SearchRepository {
        return SearchRepositoryImpl(httpClient)
    }
}