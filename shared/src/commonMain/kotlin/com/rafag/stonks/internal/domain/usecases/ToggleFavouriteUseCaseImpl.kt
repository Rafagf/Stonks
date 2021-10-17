package com.rafag.stonks.internal.domain.usecases

import com.rafag.stonks.domain.usecases.ToggleFavouriteUseCase
import com.rafag.stonks.internal.domain.repositories.FavouritesRepository

internal class ToggleFavouriteUseCaseImpl(
    private val favouritesRepository: FavouritesRepository
): ToggleFavouriteUseCase {

    override suspend fun saved(symbol: String) {
        favouritesRepository.save(symbol)
    }

    override suspend fun unsaved(symbol: String) {
        favouritesRepository.unsave(symbol)
    }
}