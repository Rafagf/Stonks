package com.rafag.stonks.data.favourites

interface FavouritesRepository {

    suspend fun getAll(): List<String>
    suspend fun save(symbol: String)
    suspend fun unsave(symbol: String)
}