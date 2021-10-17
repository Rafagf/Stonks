package com.rafag.stonks.internal.domain.usecases

import com.rafag.stonks.domain.usecases.SearchStonksUseCase
import com.rafag.stonks.domain.usecases.StonkSearch
import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import com.rafag.stonks.internal.domain.repositories.SearchItem
import com.rafag.stonks.internal.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf

internal class SearchStonksUseCaseImpl(
    private val searchRepository: SearchRepository,
    private val favouritesRepository: FavouritesRepository
): SearchStonksUseCase {

    override suspend fun invoke(query: String): Flow<List<StonkSearch>> {
        if (query.isEmpty()) return flowOf(emptyList())
        val searchSource = flowOf(searchRepository.search(query))
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