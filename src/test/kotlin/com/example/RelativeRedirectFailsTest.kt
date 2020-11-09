package com.example

import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.netty.DefaultHttpClient
import io.micronaut.http.simple.SimpleHttpRequest
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class DefaultHttpClientRedirectTest(private val httpClient: DefaultHttpClient, private val server: EmbeddedServer) {

    @Test
    fun redirectToRelativeUrl() {
        val request = getRequestTo("/redirect/relative")

        val httpResponse: HttpResponse<String> = httpClient.toBlocking().exchange(request)

        assertEquals(HttpStatus.ACCEPTED, httpResponse.status)
    }

    @Test
    fun redirectToAbsoluteUrl() {
        val request = getRequestTo("/redirect/absolute")

        val httpResponse: HttpResponse<String> = httpClient.toBlocking().exchange(request)

        assertEquals(HttpStatus.ACCEPTED, httpResponse.status)
    }

    private fun getRequestTo(path: String) : HttpRequest<String> =
            SimpleHttpRequest(HttpMethod.GET, server.url.toString() + path, null).body("")
}