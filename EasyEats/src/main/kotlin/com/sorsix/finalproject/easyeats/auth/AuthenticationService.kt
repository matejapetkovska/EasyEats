package com.sorsix.finalproject.easyeats.auth


import com.sorsix.finalproject.easyeats.configurations.JwtService
import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.enumerations.Role
import com.sorsix.finalproject.easyeats.models.exception.EmailAlreadyExist
import com.sorsix.finalproject.easyeats.models.exception.EmailNotFoundException
import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
import com.sorsix.finalproject.easyeats.models.exception.InvalidPasswordException
import com.sorsix.finalproject.easyeats.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthenticationService(private val repository: UserRepository,
                            private val passwordEncoder: PasswordEncoder,
                            private val jwtService: JwtService,
                            private val authenticationManager: AuthenticationManager) {

    fun register(request: RegisterRequest): AuthenticationResponse {
        if (request.username.isEmpty() || request.password.isEmpty() || request.email.isEmpty() || request.firstName.isEmpty() || request.lastName.isEmpty()) {
            throw InvalidArgumentsException()
        }

        val user = User(
            id = 0,
            first_name = request.firstName,
            last_name = request.lastName,
            email = request.email,
            userName = request.username,
            passw = passwordEncoder.encode(request.password),
            role = Role.USER,
            image = "default_profile_picture.jpg"
        )

        if (repository.findByEmail(request.email) != null) {
            throw EmailAlreadyExist(request.email)
        }
        repository.save(user)
        val jwtToken = jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        if (request.email.isEmpty() || request.password.isEmpty()) {
            throw InvalidArgumentsException()
        }

        val user: User = repository.findByEmail(request.email) ?: throw EmailNotFoundException()

        if (passwordEncoder.matches(request.password, user.password)) {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(request.email, request.password)
            )
            val jwtToken = jwtService.generateToken(user)
            return AuthenticationResponse(jwtToken)
        } else {
            throw InvalidPasswordException()
        }
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return email.matches(emailRegex)
    }

    fun doesUsernameExist(username: String): Boolean {
        return repository.findByUserName(username) != null
    }

    fun doesEmailExist(email: String): Boolean {
        return repository.findByEmail(email) != null
    }

    fun updateUser(updatedUser: User): User {
        val existingUser = repository.findById(updatedUser.id)
            .orElseThrow { EmailNotFoundException() }

        existingUser.first_name = updatedUser.first_name
        existingUser.last_name = updatedUser.last_name
        existingUser.email = updatedUser.email
        existingUser.passw = updatedUser.password
        existingUser.image = updatedUser.image
        existingUser.userName = updatedUser.username

        return repository.save(existingUser)
    }

}