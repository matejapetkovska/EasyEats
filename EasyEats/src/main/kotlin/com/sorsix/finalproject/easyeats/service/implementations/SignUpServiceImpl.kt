package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.service.SignUpService
import org.springframework.stereotype.Service

@Service
class SignUpServiceImpl : SignUpService {

    override fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return email.matches(emailRegex)
    }
    override fun isValidUsername(username: String): Boolean {
        val usernameRegex = Regex("^[a-zA-Z0-9.,'\\-?]+\$")
        return username.matches(usernameRegex)
    }
}