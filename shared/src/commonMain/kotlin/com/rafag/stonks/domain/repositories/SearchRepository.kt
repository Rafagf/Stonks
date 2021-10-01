package com.rafag.stonks.domain.repositories

interface SearchRepository {

    suspend fun searchRequest(symbol: String): Search
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