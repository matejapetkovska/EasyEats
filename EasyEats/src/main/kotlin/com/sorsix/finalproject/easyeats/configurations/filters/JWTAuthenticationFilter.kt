package com.sorsix.finalproject.easyeats.configurations.filters

import lombok.AllArgsConstructor
import com.fasterxml.jackson.databind.ObjectMapper
import com.sorsix.finalproject.easyeats.models.User
import com.auth0.jwt.algorithms.Algorithm
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import com.auth0.jwt.JWT
import com.sorsix.finalproject.easyeats.configurations.JWTAuthConstants
import com.sorsix.finalproject.easyeats.models.dto.UserDetailsDto
import com.sorsix.finalproject.easyeats.models.exception.InvalidPasswordException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*



@AllArgsConstructor
class JWTAuthenticationFilter(
    private var authenticationManager: AuthenticationManager?=null,
    private var userDetailsService: UserDetailsService? = null,
    private var passwordEncoder: PasswordEncoder? = null) : UsernamePasswordAuthenticationFilter() {


    @Throws(AuthenticationException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        var creds: User? = null
        try {
            creds = ObjectMapper().readValue(request.inputStream, User::class.java)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        if (creds == null) {
            throw UsernameNotFoundException("Invalid credentials")
        }
        val userDetails = userDetailsService!!.loadUserByUsername(creds.username)
        if (!passwordEncoder!!.matches(creds.password, userDetails.password)) {
            throw InvalidPasswordException()
        }
        return authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(
                userDetails.username,
                creds.password,
                userDetails.authorities
            )
        )
    }

    @Throws(IOException::class)
    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val userDetails: User = authResult.principal as User
        val token: String = JWT.create().withSubject(ObjectMapper().writeValueAsString(UserDetailsDto.of(userDetails)))
            .withExpiresAt(Date(System.currentTimeMillis() + JWTAuthConstants.EXPIRATION_TIME))
            .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET))
        response.addHeader(JWTAuthConstants.HEADER_STRING, JWTAuthConstants.TOKEN_PREFIX + token)
        response.writer.append(token)
    }
}
