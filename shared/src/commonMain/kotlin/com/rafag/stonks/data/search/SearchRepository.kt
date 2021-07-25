package com.rafag.stonks.data.search

import com.rafag.stonks.data.search.internal.Search

interface SearchRepository {

    suspend fun searchRequest(symbol: String): Search
}