package com.rafag.stonks.internal.data.httpclient

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineBase
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.callContext
import io.ktor.client.features.HttpTimeout
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.util.InternalAPI
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.collections.set

//All Kudos to my awesome co-worker Adam Brown https://github.com/ouchadam

class MockHttpClient {

    private val handlers = mutableMapOf<HttpRequest<*>, Any>()

    private val httpClient = HttpClient(object : InMemoryEngine() {
        override fun handler(requestData: HttpRequestData): Any {
            return handlers.getValue(handlers.keys.first {
                it.url == requestData.url.path()
            })
        }
    })

    internal val instance = StonksHttpClient(
        httpClient,
        baseUrl = "https://foo.com/",
    )

    internal fun <T : Any> addHandler(request: HttpRequest<T>, value: T) {
        handlers[request] = value
    }

    internal fun <T : Any> addHandler(request: HttpRequest<T>, value: Exception) {
        handlers[request] = value
    }

    private fun Url.path(): String {
        return this.toString().substringAfter(this.host).removePrefix("/").removeToken(API_TOKEN)
    }

    private fun String.removeToken(token: CharSequence): String {
        if (endsWith(token)) {
            return substring(0, length - token.length)
        }
        return this
    }
}

abstract class InMemoryEngine : HttpClientEngineBase("in-memory") {

    override val config = HttpClientEngineConfig()
    override val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @KtorExperimentalAPI
    override val supportedCapabilities = setOf(HttpTimeout)

    @InternalAPI
    override suspend fun execute(data: HttpRequestData): HttpResponseData {
        val response = handler(data)
        if (response is Exception) {
            throw response
        }
        return HttpResponseData(HttpStatusCode.OK, GMTDate(0), Headers.Empty, HttpProtocolVersion.HTTP_2_0, response, callContext())
    }

    abstract fun handler(requestData: HttpRequestData): Any
}
