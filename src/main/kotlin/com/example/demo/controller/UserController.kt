package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.repository.UserRepository
import com.example.demo.request.UserCreateRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
class UserController(
    private val userRepository: UserRepository
) {
    @GetMapping("users")
    fun all(): MutableIterable<User> {
        return userRepository.findAll()
    }


    @PostMapping("users")
    fun create(@RequestBody userCreateRequest: UserCreateRequest): User {
        val user = User(email = userCreateRequest.email ?: "", password = userCreateRequest.password ?: "")
        userRepository.save(user)
        return user
    }

    @GetMapping("users/{id}")
    fun get(@PathVariable ("id") id: Long): Optional<User> {
        val user = userRepository.findById(id)
        return user
    }
}
