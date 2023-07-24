package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.User


interface UserService {
    fun login(username: String?, password: String?): User?
}