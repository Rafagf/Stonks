package com.rafag.stonks.api

import io.ktor.client.utils.EmptyContent
import io.ktor.http.content.OutgoingContent

data class HttpRequest<T>(
    val url: String,
    val method: Method,
    val body: OutgoingContent = EmptyContent,
)

enum class Method { GET, POST, DELETE }