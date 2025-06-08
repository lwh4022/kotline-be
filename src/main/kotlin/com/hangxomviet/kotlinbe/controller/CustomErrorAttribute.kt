package com.hangxomviet.kotlinbe.controller

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest

@Component
class CustomErrorAttribute : DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest?, options: ErrorAttributeOptions?): Map<String, Any?> {

        val error = getError(request)
        val defaultAttributes = super.getErrorAttributes(request, options)


        return mapOf(
            "status" to defaultAttributes["status"],
            "error" to (error.message ?: "Unexpected error"),
            "path" to request?.path(),
            "timestamp" to java.time.Instant.now().toString()
        )
    }
}