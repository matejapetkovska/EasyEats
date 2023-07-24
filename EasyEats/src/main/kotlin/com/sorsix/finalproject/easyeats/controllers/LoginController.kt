package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
import com.sorsix.finalproject.easyeats.models.exception.InvalidPasswordException
import com.sorsix.finalproject.easyeats.models.exception.UsernameNotFoundException
import com.sorsix.finalproject.easyeats.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:4200"])
class LoginController(val service: UserService) {

    data class LoginRequest(val username: String, val password: String)
    data class ErrorResponse(val message: String)


    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        if (loginRequest.username.isBlank() || loginRequest.password.isBlank()) {
            val errorResponse = ErrorResponse("Fill the field(s)")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        }

        return try {
            val user = service.login(loginRequest.username, loginRequest.password)

            if (user != null) {
                ResponseEntity.ok(user)
            } else {
                val errorResponse = ErrorResponse("Invalid credentials")
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
            }
        } catch (exception: UsernameNotFoundException) {
            val errorResponse = ErrorResponse("Username is not valid")
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
        } catch (exception: InvalidArgumentsException) {
            val errorResponse = ErrorResponse("Username and/or password is not valid")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        } catch (exception: InvalidPasswordException) {
            val errorResponse = ErrorResponse("Password is not valid")
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
        } catch (exception: Exception) {
            val errorResponse = ErrorResponse("Unknown error occurred")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
        }
    }
}

