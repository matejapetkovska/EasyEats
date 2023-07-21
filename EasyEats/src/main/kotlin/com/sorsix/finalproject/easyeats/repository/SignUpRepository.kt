package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SignUpRepository : JpaRepository<User, Long?> {

    fun findByUsername(username: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
}