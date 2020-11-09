package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.server.util.HttpHostResolver
import io.micronaut.http.simple.SimpleHttpResponseFactory

@Controller("/redirect")
class RedirectingController (private val httpHostResolver: HttpHostResolver){

    @Get("/relative")
    fun getRelativeRedirect(): HttpResponse<Int> {
        println("RedirectingController.getRelativeRedirect")
        return SimpleHttpResponseFactory.INSTANCE
                .status(HttpStatus.TEMPORARY_REDIRECT, 1)
                .header("Location", "/redirect/target")
    }

    @Get("/absolute")
    fun getAbsoluteRedirect(httpRequest: HttpRequest<Any>): HttpResponse<Int> {
        println("RedirectingController.getAbsoluteRedirect")
        return SimpleHttpResponseFactory.INSTANCE
                .status(HttpStatus.TEMPORARY_REDIRECT, 2)
                .header("Location", "${httpHostResolver.resolve(httpRequest)}/redirect/target")
    }

    @Get("/target")
    fun getTarget(): HttpResponse<String> {
        println("RedirectingController.getTarget")
        return SimpleHttpResponseFactory.INSTANCE.status(HttpStatus.ACCEPTED,"Target")
    }
}
