package com.hangxomviet.kotlinbe.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "database")
data class DBProperties @ConstructorBinding constructor(
    val host: String,
    val port : Int,
    val userName : String,
    val password : String,
    val database : String,
)
