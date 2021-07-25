package com.rafag.stonks.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://finnhub.io/api/v1/"
private const val TOKEN = "&token=c1o8a9237fkqrr9sc3a0"

class StonksHttpClient {

    private val client: HttpClient = defaultHttpClient()

    internal suspend inline fun <reified T> execute(request: HttpRequest<T>): T {
        return client.request("$BASE_URL${request.url}$TOKEN") {
            this.method = HttpMethod.Get
            this.body = request.body
        }
    }
}

private fun defaultHttpClient() = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        level = LogLevel.HEADERS
        logger = object : Logger {
            override fun log(message: String) {
                print("Api: message = $message")
            }
        }
    }
}
