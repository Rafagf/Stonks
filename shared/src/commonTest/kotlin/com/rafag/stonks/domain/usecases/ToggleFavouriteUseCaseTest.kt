package com.rafag.stonks.domain.usecases

import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import com.rafag.stonks.mock
import com.rafag.stonks.runBlocking
import com.rafag.stonks.verify
import kotlin.test.Test

private const val A_SYMBOL = "GOOG"

class ToggleFavouriteUseCaseTest {

    private val favouritesRepository = mock<FavouritesRepository>()
    private val useCase = ToggleFavouriteUseCase(favouritesRepository)

    @Test
    fun `when saved then propagate to repository`() = runBlocking {
        useCase.saved(A_SYMBOL)

        verify(favouritesRepository).save(A_SYMBOL)
    }

    @Test
    fun `when unsaved then propagate to repository`() = runBlocking {
        useCase.unsaved(A_SYMBOL)

        verify(favouritesRepository).unsave(A_SYMBOL)
    }
}