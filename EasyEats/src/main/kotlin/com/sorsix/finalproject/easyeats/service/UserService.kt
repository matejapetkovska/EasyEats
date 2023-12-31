package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.User
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.multipart.MultipartFile
import java.security.Principal


interface UserService : UserDetailsService {
    fun isValidEmail(email: String): Boolean
    fun isValidUsername(username: String): Boolean
    fun doesUsernameExist(username: String): Boolean
    fun doesEmailExist(email: String): Boolean
    fun getLoggedInUser(request: HttpServletRequest): User?
    fun updateUser(userId: Long, updatedUser: User, principal: Principal): User
    fun getUserFromToken(token: String): User?
    fun updateImageField(user_id: Long, image: MultipartFile): User?
    fun getUserFromId(user_id: Long): User?
}


