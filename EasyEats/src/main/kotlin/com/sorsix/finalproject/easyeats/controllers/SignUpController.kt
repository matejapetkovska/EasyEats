package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.enumerations.Role
import com.sorsix.finalproject.easyeats.models.exception.Error
import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
import com.sorsix.finalproject.easyeats.models.exception.PasswordDoNotMatch
import com.sorsix.finalproject.easyeats.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = ["http://localhost:4200"])
class SignUpController(val userService: UserService) {

    data class UserRegistrationRequest(
        val username: String,
        val email: String,
        val password: String,
        val repeatPass: String,
        val first_name: String,
        val last_name: String,
        val role: Role,
        val image: String
    )

    @PostMapping
    fun signUp(@RequestBody request: UserRegistrationRequest): ResponseEntity<Any> {

        try {
            if (request.username.isNullOrBlank() || request.email.isNullOrBlank() || request.password.isNullOrBlank() ||
                request.repeatPass.isNullOrBlank() || request.first_name.isNullOrBlank() || request.last_name.isNullOrBlank()
            ) {
                val errorMessage = "Please fill in all required fields."
                return ResponseEntity.badRequest().body(Error(errorMessage))
            }

            if (!userService.isValidEmail(request.email)) {
                val errorMessage = "Please enter a valid email address."
                return ResponseEntity.badRequest().body(Error(errorMessage))
            }

            if (request.password.length < 6) {
                val errorMessage = "Password must be at least 6 characters long."
                return ResponseEntity.badRequest().body(Error(errorMessage))
            }

            if(userService.doesUsernameExist(request.username) && userService.doesEmailExist(request.email)){
                val errorMessage = "Username and Email already exists."
                return ResponseEntity.badRequest().body(Error(errorMessage))
            }

            if (userService.doesUsernameExist(request.username)) {
                val errorMessage = "Username already exists."
                return ResponseEntity.badRequest().body(Error(errorMessage))
            }

            if (userService.doesEmailExist(request.email)) {
                val errorMessage = "Email already exists."
                return ResponseEntity.badRequest().body(Error(errorMessage))
            }

            val savedUser = userService.register(
                request.username,
                request.email,
                request.password,
                request.repeatPass,
                request.first_name,
                request.last_name,
                Role.USER,
                request.image
            ) ?: return ResponseEntity.badRequest().body(Error("User registration failed."))

            return ResponseEntity.ok(savedUser)
        } catch (exception: InvalidArgumentsException) {
            return ResponseEntity.badRequest().body(Error(exception.message))
        } catch (exception: PasswordDoNotMatch) {
            return ResponseEntity.badRequest().body(Error(exception.message))
        }
    }
}

