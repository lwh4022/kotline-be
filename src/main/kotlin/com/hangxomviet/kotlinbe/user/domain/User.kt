package com.hangxomviet.kotlinbe.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("users")
data class User(
    @Id
    val id : UUID = UUID.randomUUID(),
    val name : String,
    val email : String,
    val password: String,
    val role : Role = Role.USER,

    @Column("created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)

enum class Role {
    USER,
    INSTRUCTOR
}