package com.rafag.stonks.api.internal

import io.ktor.client.utils.EmptyContent
import io.ktor.http.content.OutgoingContent

internal data class HttpRequest<T>(
    val url: String,
    val method: Method,
    val body: OutgoingContent = EmptyContent,
)

internal enum class Method { GET, POST, DELETE }