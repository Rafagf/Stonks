package com.rafag.stonks.data.favourites

import kotlinx.serialization.descriptors.PrimitiveKind.*

interface FavouritesRepository {
    fun getAll(): List<String>
    fun save(symbol: String)
    fun unsave(symbol: String)
}