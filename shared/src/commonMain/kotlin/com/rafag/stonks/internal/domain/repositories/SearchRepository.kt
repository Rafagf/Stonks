package com.rafag.stonks.internal.domain.repositories

internal interface SearchRepository {

    suspend fun search(symbol: String): Search
}

data class Search(
    val list: List<SearchItem>,
)

data class SearchItem(
    val name: String,
    val displaySymbol: String,
    val symbol: String,
    val type: String,
)