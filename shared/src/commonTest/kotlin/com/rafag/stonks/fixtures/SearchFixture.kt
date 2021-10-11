package com.rafag.stonks.fixtures

import com.rafag.stonks.internal.api.ApiSearchItemResponse
import com.rafag.stonks.internal.api.ApiSearchResponse

internal object SearchFixture {

    fun anApiSearch() = ApiSearchResponse(
        listOf(anApiSearchItem())
    )

    private fun anApiSearchItem() = ApiSearchItemResponse(
        description = "a-description",
        displaySymbol = "a-display-symbol",
        symbol = "symbol",
        type = "a-type"
    )
}
