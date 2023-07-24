package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.exception.Error
import com.sorsix.finalproject.easyeats.repository.SignUpRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:4200"])
class SignUpController(val userRepository: SignUpRepository) {

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return email.matches(emailRegex)
    }

    fun isValidUsername(username: String): Boolean {
        val usernameRegex = Regex("^[a-zA-Z0-9.,'\\-?]+\$")
        return username.matches(usernameRegex)
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody user: User): ResponseEntity<Any> {
        if (user.username.isBlank() || user.email.isBlank() || user.password.isBlank() ||
            user.first_name.isBlank() || user.last_name.isBlank()) {
            return ResponseEntity.badRequest().body(Error("Please fill in all required fields."))
        }

        if (!isValidEmail(user.email)) {
            return ResponseEntity.badRequest().body(Error("Please enter a valid email address."))
        }

        if (user.password.length < 6) {
            return ResponseEntity.badRequest().body(Error("Password must be at least 6 characters long."))
        }

        if (!isValidUsername(user.username)) {
            return ResponseEntity.badRequest().body(Error("Please enter a valid username."))
        }

        val existingUserByUsername: Optional<User> = userRepository.findByUsername(user.username)
        if (existingUserByUsername.isPresent) {
            println(ResponseEntity.status(HttpStatus.CONFLICT).body(Error("Username already exists.")))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Error("Username already exists."))
        }

        val existingUserByEmail: Optional<User> = userRepository.findByEmail(user.email)
        if (existingUserByEmail.isPresent) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Error("Email already exists."))
        }

        val savedUser = userRepository.save(user)

        return ResponseEntity.ok(savedUser)
    }

}