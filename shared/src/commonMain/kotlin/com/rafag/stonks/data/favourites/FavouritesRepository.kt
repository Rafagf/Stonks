package com.rafag.stonks.data.favourites

interface FavouritesRepository {
    fun save(symbol: String)
    fun unsave(symbol: String)
}