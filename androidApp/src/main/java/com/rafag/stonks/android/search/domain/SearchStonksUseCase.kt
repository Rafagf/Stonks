package com.rafag.stonks.android.search.domain

import com.rafag.stonks.data.favourites.FavouritesRepository
import com.rafag.stonks.data.search.SearchItem
import com.rafag.stonks.data.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

class SearchStonksUseCase(
    private val searchRepository: SearchRepository,
    private val favouritesRepository: FavouritesRepository
) {

    suspend fun search(query: String): Flow<List<StonkSearch>> {
        val searchSource = flowOf(searchRepository.searchRequest(query))
        val favsSource = favouritesRepository.getAll()
        return searchSource.combine(favsSource) { search, favs ->
            search.list.map { searchItem ->
                searchItem.toStonkSearch(
                    faved = favs.contains(searchItem.symbol)
                )
            }
        }
    }
}

private fun SearchItem.toStonkSearch(faved: Boolean) = StonkSearch(
    name = name,
    symbol = displaySymbol,
    faved = faved,
)

data class StonkSearch(
    val name: String,
    val symbol: String,
    val faved: Boolean
)