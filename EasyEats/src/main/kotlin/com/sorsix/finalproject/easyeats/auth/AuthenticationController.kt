package com.sorsix.finalproject.easyeats.auth

import com.sorsix.finalproject.easyeats.models.exception.*
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
class AuthenticationController(private val service: AuthenticationService) {

    data class ErrorResponse(val message: String)

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest): ResponseEntity<Any>{
        try {
            if (request.username.isBlank() || request.email.isBlank() || request.password.isBlank() ||
                  request.firstName.isBlank() || request.lastName.isBlank()
            ) {
                val errorMessage = "Please fill in all required fields."
                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
            }

            if (!service.isValidEmail(request.email)) {
                val errorMessage = "Please enter a valid email address."
                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
            }

            if (request.password.length < 6) {
                val errorMessage = "Password must be at least 6 characters long."
                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
            }

            if(service.doesUsernameExist(request.username) && service.doesEmailExist(request.email)){
                val errorMessage = "Username and Email already exists."
                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
            }

            if (service.doesUsernameExist(request.username)) {
                val errorMessage = "Username already exists."
                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
            }

            if (service.doesEmailExist(request.email)) {
                val errorMessage = "Email already exists."
                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
            }

            val savedUser = service.register(request)
            return ResponseEntity.ok(savedUser)
        } catch (exception: InvalidArgumentsException) {
            return ResponseEntity.badRequest().body(exception.message?.let { ErrorResponse(it) })
        } catch (exception: PasswordDoNotMatch) {
            return ResponseEntity.badRequest().body(exception.message?.let { ErrorResponse(it) })
        }

    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody request: AuthenticationRequest): ResponseEntity<Any>{

        if (request.email.isNullOrEmpty() || request.password.isNullOrEmpty()) {
            val errorResponse = ErrorResponse("Please fill in all required fields.")
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        }

        return try {
            ResponseEntity.ok(service.authenticate(request))
        } catch (exception: EmailNotFoundException) {
            val errorResponse = ErrorResponse("Email not found")
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
        } catch (exception: InvalidArgumentsException) {
            val errorResponse = ErrorResponse("Invalid Arguments")
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
        } catch (exception: InvalidPasswordException) {
            val errorResponse = ErrorResponse("Invalid password")
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
        } catch (exception: Exception) {
            val errorResponse = ErrorResponse("Unknown error occurred")
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
        }

    }
}