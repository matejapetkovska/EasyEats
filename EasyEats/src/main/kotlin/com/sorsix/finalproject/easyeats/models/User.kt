package com.sorsix.finalproject.easyeats.models

import com.sorsix.finalproject.easyeats.models.enumerations.Role
import jakarta.persistence.*

@Entity
@Table(name="users")
class User (

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long = 0,

    val first_name: String,

    val last_name: String,

    val email: String,

    @Column(unique = true)
    val username: String,

    val password: String,

    @Enumerated(value = EnumType.STRING)
    val role: Role,

    val image: String

    ){ constructor() : this(0, "", "", "", "", "", Role.USER, "") {}
}