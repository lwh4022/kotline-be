package com.hangxomviet.kotlinbe.user.repository

import com.hangxomviet.kotlinbe.user.domain.User
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.convert.MappingR2dbcConverter
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val template : R2dbcEntityTemplate,
    private val converter : MappingR2dbcConverter
    ) {

    suspend fun fetchUsers() : List<User>? {
        val sql = """
            SELECT id
                 , name
                 , email
                 , password
                 , role
                 , created_at
             FROM users
        """.trimIndent()

        return template.databaseClient
            .sql(sql)
            .map { row, metaData ->  converter.read(User::class.java, row, metaData)}
            .all()
            .collectList()
            .awaitSingle()

    }

    suspend fun fetchUser(id : String) : User? {
        val sql = """
            SELECT id
                 , name
                 , email
                 , password
                 , role
                 , created_at
             FROM users
            WHERE id::text = :id
        """.trimIndent()

        return template.databaseClient
            .sql(sql)
            .bind("id", id)
            .map { row, metadata -> converter.read(User::class.java, row, metadata) }
            .one()
            .awaitSingle()
    }






}

