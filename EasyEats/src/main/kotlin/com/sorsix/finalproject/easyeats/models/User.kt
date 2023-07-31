package com.sorsix.finalproject.easyeats.models

import com.sorsix.finalproject.easyeats.models.enumerations.Role
import jakarta.persistence.*

@Entity
@Table(name="users")
class User (

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long = 0,

    var first_name: String,

    var last_name: String,

    var email: String,

    @Column(unique = true)
    var username: String,

    var password: String,

    @Enumerated(value = EnumType.STRING)
    val role: Role,

    var image: String

    ){ constructor() : this(0, "", "", "", "", "", Role.USER, "") {}
}