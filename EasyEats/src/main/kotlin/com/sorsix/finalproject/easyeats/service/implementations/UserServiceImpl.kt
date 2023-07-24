package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
import com.sorsix.finalproject.easyeats.models.exception.InvalidUser
import com.sorsix.finalproject.easyeats.repository.SignUpRepository
import com.sorsix.finalproject.easyeats.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val repository: SignUpRepository): UserService {

    override fun login(username: String?, password: String?): User? {
        if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            throw InvalidArgumentsException()
        }
        val user = repository.findByUsername(username).orElseThrow { InvalidArgumentsException() }
        if (password == user.password) {
            return user
        } else {
            throw InvalidUser()
        }
    }

}