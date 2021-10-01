package com.rafag.stonks.domain.usecases

import com.rafag.stonks.domain.repositories.FavouritesRepository

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