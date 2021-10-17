package com.rafag.stonks.domain.usecases

import kotlinx.coroutines.flow.Flow

interface FetchFavedQuotesUseCase {

    suspend fun invoke(): Flow<List<FavedQuote>>
}

data class FavedQuote(
    val symbol: String,
    val current: Double,
    val open: Double,
)