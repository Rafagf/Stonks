package com.rafag.stonks.domain.repositories

import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(symbol: String): Flow<Search>
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