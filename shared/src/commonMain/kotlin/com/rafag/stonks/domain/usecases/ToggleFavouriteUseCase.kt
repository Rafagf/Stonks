package com.rafag.stonks.domain.usecases

interface ToggleFavouriteUseCase {

    suspend fun saved(symbol: String)
    suspend fun unsaved(symbol: String)
}

