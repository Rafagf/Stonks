package com.rafag.stonks.internal.data.repositories.favourites

import com.rafag.stonks.internal.domain.repositories.FavouritesRepository
import kotlinx.coroutines.flow.Flow

internal class FavouritesRepositoryImpl(
    private val persistence: FavouritesPersistence
) : FavouritesRepository {

    override suspend fun getAll(): Flow<List<String>> {
        return try {
            persistence.getAll()
        } catch (exception: Exception) {
            throw ErrorFetchingFavourites(exception)
        }
    }

    override suspend fun save(symbol: String) {
        try {
            persistence.save(symbol)
        } catch (exception: Exception) {
            throw ErrorSavingFavourite(symbol, exception)
        }
    }

    override suspend fun unsave(symbol: String) {
        try {
            persistence.unsave(symbol)
        } catch (exception: Exception) {
            throw ErrorUnSaveFavourite(symbol, exception)
        }
    }

    data class ErrorFetchingFavourites(override val cause: Throwable) : Throwable("Could not fetch favourites -  $cause")
    data class ErrorSavingFavourite(val symbol: String, override val cause: Throwable) : Throwable("Symbol $symbol couldn't be saved - $cause")
    data class ErrorUnSaveFavourite(val symbol: String, override val cause: Throwable) : Throwable("Symbol $symbol couldn't be unsaved -  $cause")
}