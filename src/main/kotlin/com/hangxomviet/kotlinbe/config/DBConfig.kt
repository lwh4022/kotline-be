package com.hangxomviet.kotlinbe.config

import io.r2dbc.pool.ConnectionPool
import io.r2dbc.pool.ConnectionPoolConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import java.time.Duration

@Configuration
// R2BCRepository 구성을 활성화
@EnableR2dbcRepositories
// Auditing 활성화
@EnableR2dbcAuditing
class DBConfig(private val dbProperties: DBProperties) : AbstractR2dbcConfiguration() {

    @Bean
    override fun connectionFactory(): ConnectionFactory {

        val connectionFactory =  PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host(dbProperties.host)
                .port(dbProperties.port)
                .username(dbProperties.userName)
                .password(dbProperties.password)
                .database(dbProperties.database)
                .connectTimeout(Duration.ofSeconds(5))
                .build()
        )

        val configuration = ConnectionPoolConfiguration.builder(connectionFactory)
            .maxIdleTime(Duration.ofSeconds(5))
            .maxSize(25)
            .minIdle(25)
            .initialSize(25)
            .validationQuery("SELECT 1")
            .build()

        val pool = ConnectionPool(configuration)

        pool.warmup().block()

        return pool



    }

}