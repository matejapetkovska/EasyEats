package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.enumerations.Role
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.multipart.MultipartFile


interface UserService : UserDetailsService{
    fun isValidEmail(email: String): Boolean
    fun isValidUsername(username: String): Boolean
    fun doesUsernameExist(username: String): Boolean
    fun doesEmailExist(email: String): Boolean
    fun getLoggedInUser(request: HttpServletRequest): User?
    fun updateUser(updatedUser: User): User

    fun getUserFromToken(token: String): User?

}


