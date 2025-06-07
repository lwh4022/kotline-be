package com.hangxomviet.kotlinbe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KotlinBeApplication

fun main(args: Array<String>) {
	runApplication<KotlinBeApplication>(*args)
}
