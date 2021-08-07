package com.rafag.stonks

import com.rafag.stonks.api.StonksHttpClient
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.date.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.rafag.stonks.api.internal.HttpRequest

//All Kudos to my awesome co-worker Adam Brown https://github.com/ouchadam

class MockHttpClient {

    private val handlers = mutableMapOf<HttpRequest<*>, Any>()

    private val httpClient = HttpClient(object : InMemoryEngine() {
        override fun handler(requestData: HttpRequestData): Any {
            return handlers.getValue(handlers.keys.first { it.url == requestData.url.path() })
        }
    })

    val instance = StonksHttpClient(
        httpClient,
        baseUrl = "http://foo.com/",
    )

    fun <T : Any> addHandler(request: HttpRequest<T>, value: T) {
        handlers[request] = value
    }

    fun <T : Any> addHandler(request: HttpRequest<T>, value: Exception) {
        handlers[request] = value
    }

    private fun Url.path(): String {
        return this.toString().substringAfter(this.host).removePrefix("/")
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
