package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.enumerations.Role
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.UserDetailsService


interface UserService : UserDetailsService{
    fun register(username: String?, email: String?, password: String?, repeatPassword: String?, name: String?, surname: String?, role: Role?, image: String?, request: HttpServletRequest): User?
    fun isValidEmail(email: String): Boolean
    fun isValidUsername(username: String): Boolean
    fun doesUsernameExist(username: String): Boolean
    fun doesEmailExist(email: String): Boolean
    fun login(username: String?, password: String?, request: HttpServletRequest): User?
    fun getLoggedInUser(request: HttpServletRequest): User?
}


