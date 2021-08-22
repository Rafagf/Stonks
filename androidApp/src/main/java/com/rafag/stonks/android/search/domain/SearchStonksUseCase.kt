package com.rafag.stonks.android.search

import com.rafag.stonks.data.search.SearchItem
import com.rafag.stonks.data.search.SearchRepository
import kotlin.random.Random

//TODO to live in a separate domain model
//TODO to merge with favorites to show liked stonks in search
class SearchStonksUseCase(private val searchRepository: SearchRepository) {

    suspend fun search(query: String): List<StonkSearch> {
        return searchRepository.searchRequest(query).list.map {
            it.toStonkSearch()
        }
    }
}

private fun SearchItem.toStonkSearch() = StonkSearch(
    name = name,
    symbol = displaySymbol,
    faved = Random.nextBoolean(),
)

data class StonkSearch(
    val name: String,
    val symbol: String,
    val faved: Boolean
)