package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.enumerations.Role
import org.springframework.security.core.userdetails.UserDetailsService


interface UserService : UserDetailsService{
    fun login(username: String?, password: String?): User?
    //fun register(username: String?, password: String?, name: String?, surname: String?, role: Role?): User?
}

