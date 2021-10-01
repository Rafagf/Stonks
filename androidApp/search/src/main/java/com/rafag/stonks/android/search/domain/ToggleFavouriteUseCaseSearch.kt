package com.rafag.stonks.android.search.domain

import com.rafag.stonks.data.favourites.FavouritesRepository
import com.rafag.stonks.data.search.SearchItem
import com.rafag.stonks.data.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

class ToggleFavouriteUseCaseSearch(
    private val favouritesRepository: FavouritesRepository
)  {

    suspend fun saved(symbol: String) {
        favouritesRepository.save(symbol)
    }

    suspend fun unsaved(symbol: String) {
        favouritesRepository.unsave(symbol)
    }
}