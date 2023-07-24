package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.exception.InvalidUser
import com.sorsix.finalproject.easyeats.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:4200"])
class LoginController(val service: UserService) {

    data class LoginRequest(val username: String, val password: String)


    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<*>? {
        return try {
            val user: User? = service.login(loginRequest.username, loginRequest.password)
            ResponseEntity.ok<Any>(user)
        } catch (e: InvalidUser) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body<String>("Invalid credentials")
        }
    }
}