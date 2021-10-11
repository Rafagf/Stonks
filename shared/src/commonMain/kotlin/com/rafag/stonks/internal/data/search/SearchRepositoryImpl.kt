package com.rafag.stonks.internal.data.search

import com.rafag.stonks.internal.api.StonksHttpClient
import com.rafag.stonks.internal.api.ApiSearchItemResponse
import com.rafag.stonks.internal.api.ApiSearchResponse
import com.rafag.stonks.internal.api.SearchApi
import com.rafag.stonks.domain.repositories.Search
import com.rafag.stonks.domain.repositories.SearchItem
import com.rafag.stonks.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class SearchRepositoryImpl(
    private val httpClient: StonksHttpClient
) : SearchRepository {

    override suspend fun search(symbol: String): Search {
        try {
            return httpClient.execute(SearchApi.searchRequest(symbol)).toModel()
        } catch (exception: Exception) {
            throw ErrorSearching(symbol, exception)
        }
    }
}

data class ErrorSearching(val symbol: String, override val cause: Throwable) : Throwable("Could not fetch symbols -  $cause")


internal fun ApiSearchResponse.toModel() = Search(
    list = result.map { it.toModel() }
)

private fun ApiSearchItemResponse.toModel() = SearchItem(
    name = description,
    displaySymbol = displaySymbol,
    symbol = symbol,
    type = type
)
