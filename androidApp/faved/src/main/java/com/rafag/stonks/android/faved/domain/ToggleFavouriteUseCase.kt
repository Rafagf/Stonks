package com.rafag.stonks.android.faved.domain

import com.rafag.stonks.data.favourites.FavouritesRepository
import com.rafag.stonks.data.search.SearchItem
import com.rafag.stonks.data.search.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.zip

//TODO duplicated, will be moved to shared
class ToggleFavouriteUseCase(
    private val favouritesRepository: FavouritesRepository
)  {

    suspend fun saved(symbol: String) {
        favouritesRepository.save(symbol)
    }

    suspend fun unsaved(symbol: String) {
        favouritesRepository.unsave(symbol)
    }
}