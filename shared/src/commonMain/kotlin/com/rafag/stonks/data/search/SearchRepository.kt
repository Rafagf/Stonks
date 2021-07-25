package com.rafag.stonks.data.search

interface SearchRepository {

    suspend fun searchRequest(symbol: String): Search
}

data class Search(
    val list: List<SearchItem>,
)

data class SearchItem(
    val description: String,
    val displaySymbol: String,
    val symbol: String,
    val type: String,
)