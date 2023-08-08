//package com.sorsix.finalproject.easyeats.controllers
//
//import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
//import com.sorsix.finalproject.easyeats.models.enumerations.Role
//import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
//import com.sorsix.finalproject.easyeats.models.exception.PasswordDoNotMatch
//import com.sorsix.finalproject.easyeats.service.UserService
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//import org.springframework.web.multipart.MultipartFile
//import java.util.*
//
//@RestController
//@RequestMapping("/signup")
//@CrossOrigin(origins = ["http://localhost:4200"])
//class SignUpController(val userService: UserService) {
//
//    data class UserRegistrationRequest(
//        val username: String,
//        val email: String,
//        val password: String,
//        val repeatPass: String,
//        val first_name: String,
//        val last_name: String,
//        val role: Role,
//        val image: String
//    )
//
//    data class ErrorResponse(val message: String)
//
//    @PostMapping
//    fun signUp(@RequestParam request: String,
//               @RequestParam file: MultipartFile,
//               httprequest: HttpServletRequest): ResponseEntity<Any> {
//        val objectMapper = jacksonObjectMapper()
//        val user: UserRegistrationRequest = objectMapper.readValue(request, UserRegistrationRequest::class.java)
//        try {
//            if (user.username.isBlank() || user.email.isBlank() || user.password.isBlank() ||
//                user.repeatPass.isBlank() || user.first_name.isBlank() || user.last_name.isBlank()
//            ) {
//                val errorMessage = "Please fill in all required fields."
//                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
//            }
//
//            if (!userService.isValidEmail(user.email)) {
//                val errorMessage = "Please enter a valid email address."
//                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
//            }
//
//            if (user.password.length < 6) {
//                val errorMessage = "Password must be at least 6 characters long."
//                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
//            }
//
//            if(userService.doesUsernameExist(user.username) && userService.doesEmailExist(user.email)){
//                val errorMessage = "Username and Email already exists."
//                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
//            }
//
//            if (userService.doesUsernameExist(user.username)) {
//                val errorMessage = "Username already exists."
//                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
//            }
//
//            if (userService.doesEmailExist(user.email)) {
//                val errorMessage = "Email already exists."
//                return ResponseEntity.badRequest().body(ErrorResponse(errorMessage))
//            }
//
//            val savedUser = userService.register(
//                user.username,
//                user.email,
//                user.password,
//                user.repeatPass,
//                user.first_name,
//                user.last_name,
//                Role.USER,
//                file,
//                httprequest
//            ) ?: return ResponseEntity.badRequest().body(ErrorResponse("User registration failed."))
//
//            httprequest.session.setAttribute("user", savedUser)
//            return ResponseEntity.ok(savedUser)
//        } catch (exception: InvalidArgumentsException) {
//            return ResponseEntity.badRequest().body(exception.message?.let { ErrorResponse(it) })
//        } catch (exception: PasswordDoNotMatch) {
//            return ResponseEntity.badRequest().body(exception.message?.let { ErrorResponse(it) })
//        }
//
//    }
//}
//
