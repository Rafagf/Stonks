package com.rafag.stonks.data.search

import com.rafag.stonks.api.internal.ApiSearchResponse

interface SearchRepository {

    suspend fun searchRequest(symbol: String): ApiSearchResponse
}