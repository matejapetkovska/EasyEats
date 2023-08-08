//package com.sorsix.finalproject.easyeats.controllers
//
//import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
//import com.sorsix.finalproject.easyeats.models.exception.InvalidPasswordException
//import com.sorsix.finalproject.easyeats.models.exception.UsernameNotFoundException
//import com.sorsix.finalproject.easyeats.service.UserService
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true")
//class LoginController(val service: UserService) {
//
//    data class LoginRequest(val username: String, val password: String)
//    data class ErrorResponse(val message: String)
//
//
//    @PostMapping("/login")
//    fun login(@RequestBody loginRequest: LoginRequest, request: HttpServletRequest): ResponseEntity<Any> {
//        val username = loginRequest.username
//        val password = loginRequest.password
//
//        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
//            val errorResponse = ErrorResponse("Please fill in all required fields.")
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
//        }
//
//        return try {
//            val user = service.login(username, password, request)
//            request.session.setAttribute("user", user)
//            ResponseEntity.ok(user)
//        } catch (exception: UsernameNotFoundException) {
//            val errorResponse = ErrorResponse("Username not found")
//            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
//        } catch (exception: InvalidArgumentsException) {
//            val errorResponse = ErrorResponse("Invalid Arguments")
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
//        } catch (exception: InvalidPasswordException) {
//            val errorResponse = ErrorResponse("Invalid password")
//            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse)
//        } catch (exception: Exception) {
//            val errorResponse = ErrorResponse("Unknown error occurred")
//            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
//        }
//    }
//}

