package com.sorsix.finalproject.easyeats.service

interface SignUpService {
    fun isValidEmail(email: String): Boolean
    fun isValidUsername(username: String): Boolean
}