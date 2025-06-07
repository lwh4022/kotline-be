package com.hangxomviet.kotlinbe.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class DBConfig(private val dbProperties: DBProperties) : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory {


        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host(dbProperties.host)
                .port(dbProperties.port)
                .username(dbProperties.userName)
                .password(dbProperties.password)
                .database(dbProperties.database)
                .build()
        )



    }

}