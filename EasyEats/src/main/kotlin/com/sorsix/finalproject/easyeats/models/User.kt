package com.sorsix.finalproject.easyeats.models

import com.sorsix.finalproject.easyeats.models.enumerations.Role
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name="users")
class User (

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id: Long = 0,

    var first_name: String,

    var last_name: String,

    @Column(unique = true)
    var email: String,

    @Column(unique = true)
    var userName: String,

    var passw: String,

    @Enumerated(value = EnumType.STRING)
    val role: Role,

    var image: String

    ) : UserDetails
{ constructor() : this(0, "", "", "", "", "", Role.USER, "") {}

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return passw
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}