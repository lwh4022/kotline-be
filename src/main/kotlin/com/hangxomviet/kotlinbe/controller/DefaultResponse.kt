package com.hangxomviet.kotlinbe.controller

import org.springframework.http.HttpStatus

data class DefaultResponse(
    val status : String = HttpStatus.OK.name,
    val data : Any?
)