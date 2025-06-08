package com.hangxomviet.kotlinbe.controller

import com.hangxomviet.kotlinbe.user.domain.User
import com.hangxomviet.kotlinbe.user.use_case.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @GetMapping("/user/all")
    suspend fun getUser() : ResponseEntity<DefaultResponse> {
        val users: List<User>  = userService.getUsers()
        return ResponseEntity(DefaultResponse( data = users), HttpStatus.OK)
    }

    @GetMapping("/user")
    suspend fun getUser(@RequestParam id: String) : ResponseEntity<DefaultResponse> {
        val user  = userService.getUser(id)

        return ResponseEntity(DefaultResponse(data = user), HttpStatus.OK)
    }
}