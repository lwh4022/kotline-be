package com.hangxomviet.kotlinbe.controller


import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.HttpMessageWriter

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import org.springframework.web.reactive.function.server.ServerResponse.Context
import org.springframework.web.reactive.result.view.ViewResolver

data class ErrorResponse(
    val status : Int,
    val message : String,
    val data : Any?,
    val timestamp: String = java.time.Instant.now().toString()
)
@Component
@Order(-2)
class GlobalErrorController(private val errorAttribute: CustomErrorAttribute) : ErrorWebExceptionHandler {

    private val handlerStrategies = HandlerStrategies.builder().build()
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {

        // 예외를 ServerWebExchange에 등록
        exchange.attributes["org.springframework.boot.web.reactive.error.DefaultErrorAttributes.ERROR"] = ex

        val request = ServerRequest.create(exchange, listOf())

        val errorPropsMap = errorAttribute.getErrorAttributes(request, ErrorAttributeOptions.defaults())

        val responseMono = ServerResponse
            .status(errorPropsMap["status"] as Int)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(errorPropsMap))

        return responseMono.flatMap {
            it.writeTo(exchange, object : Context {
                override fun messageWriters() = handlerStrategies.messageWriters()
                override fun viewResolvers() = handlerStrategies.viewResolvers()
            })
        }
    }

}