package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long?> {

    fun findByEmail(email: String): User?
    fun findByUserName(username: String?): User?
}