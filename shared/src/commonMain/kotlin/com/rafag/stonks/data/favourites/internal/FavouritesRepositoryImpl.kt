package com.rafag.stonks.data.favourites.internal

import com.rafag.stonks.data.favourites.FavouritesRepository

internal class FavouritesRepositoryImpl(
    private val persistence: FavouritesPersistence
) : FavouritesRepository {

    override fun getAll(): List<String> {
        return try {
            persistence.getAll()
        } catch (exception: Exception) {
            throw CantFetchFavourites(exception)
        }
    }

    override fun save(symbol: String) {
        try {
            persistence.save(symbol)
        } catch (exception: Exception) {
            throw CannotSaveFavourite(symbol, exception)
        }
    }

    override fun unsave(symbol: String) {
        try {
            persistence.unsave(symbol)
        } catch (exception: Exception) {
            throw CannotUnSaveFavourite(symbol, exception)
        }
    }

    data class CantFetchFavourites(override val cause: Throwable) : Throwable("Could not fetch favourites -  $cause")
    data class CannotSaveFavourite(val symbol: String, override val cause: Throwable) : Throwable("Symbol $symbol couldn't be saved - $cause")
    data class CannotUnSaveFavourite(val symbol: String, override val cause: Throwable) : Throwable("Symbol $symbol couldn't be unsaved -  $cause")
}