package com.hangxomviet.kotlinbe.user.use_case

import com.hangxomviet.kotlinbe.user.domain.User
import com.hangxomviet.kotlinbe.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService (private val userRepository: UserRepository) {

    suspend fun getUsers() : List<User>{
        return userRepository.fetchUsers().orEmpty()
    }

    suspend fun getUser(id : String) : User? {
        return userRepository.fetchUser(id)
    }
}