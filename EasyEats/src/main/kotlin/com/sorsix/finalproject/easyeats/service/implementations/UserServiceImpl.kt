package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
import com.sorsix.finalproject.easyeats.models.exception.InvalidPasswordException
import com.sorsix.finalproject.easyeats.models.exception.UsernameNotFoundException
import com.sorsix.finalproject.easyeats.repository.SignUpRepository
import com.sorsix.finalproject.easyeats.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val repository: SignUpRepository): UserService {

    override fun login(username: String?, password: String?): User? {
        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            throw InvalidArgumentsException()
        }

        val user = repository.findByUsername(username)
        if (!user.isPresent) {
            throw UsernameNotFoundException()
        }

        if (password == user.get().password) {
            return user.get()
        } else {
            throw InvalidPasswordException()
        }
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }

}