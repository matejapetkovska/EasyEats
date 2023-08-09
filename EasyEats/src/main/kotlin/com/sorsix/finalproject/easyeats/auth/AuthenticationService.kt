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
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class AuthenticationService(private val repository: UserRepository,
                            private val passwordEncoder: PasswordEncoder,
                            private val jwtService: JwtService,
                            private val authenticationManager: AuthenticationManager) {

    fun register(request: RegisterRequest): AuthenticationResponse {

        if (request.username.isNullOrEmpty() || request.password.isNullOrEmpty() || request.email.isNullOrEmpty() || request.firstName.isNullOrEmpty() || request.lastName.isNullOrEmpty()) {
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

        if (request.email.isNullOrEmpty() || request.password.isNullOrEmpty()) {
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


//    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
//        authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(
//                request.email,
//                request.password
//            )
//        )
//        val user = repository.findByEmail(request.email)
//        if (user == null){
//            throw UsernameNotFoundException("")
//        }
//
//        val jwtToken = jwtService.generateToken(user)
//        val refreshToken = jwtService.generateRefreshToken(user)
//
//        revokeAllUserTokens(user)
//        saveUserToken(user, jwtToken)
//
//        return AuthenticationResponse.builder()
//            .accessToken(jwtToken)
//            .refreshToken(refreshToken)
//            .build()
//    }
//
//    private fun saveUserToken(user: User, jwtToken: String) {
//        val token = Token.builder()
//            .user(user)
//            .token(jwtToken)
//            .tokenType(TokenType.BEARER)
//            .expired(false)
//            .revoked(false)
//            .build()
//        tokenRepository.save(token)
//    }
//
//    private fun revokeAllUserTokens(user: User) {
//        val validUserTokens = tokenRepository.findAllValidTokenByUser(user.id)
//        if (validUserTokens.isEmpty())
//            return
//
//        validUserTokens.forEach { token ->
//            token.expired = true
//            token.revoked = true
//        }
//
//        tokenRepository.saveAll(validUserTokens)
//    }
//
//    @Throws(IOException::class)
//    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
//        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
//        val refreshToken: String
//        val userEmail: String?
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            return
//        }
//        refreshToken = authHeader.substring(7)
//        userEmail = jwtService.extractUsername(refreshToken)
//
//        if (userEmail != null) {
//            val user = repository.findByEmail(userEmail)
//                .orElseThrow()
//
//            if (jwtService.isTokenValid(refreshToken, user)) {
//                val accessToken = jwtService.generateToken(user)
//                revokeAllUserTokens(user)
//                saveUserToken(user, accessToken)
//
//                val authResponse = AuthenticationResponse.builder()
//                    .accessToken(accessToken)
//                    .refreshToken(refreshToken)
//                    .build()
//
//                val objectMapper = ObjectMapper()
//                objectMapper.writeValue(response.getOutputStream(), authResponse)
//            }
//        }
//    }



     fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return email.matches(emailRegex)
    }

     fun isValidUsername(username: String): Boolean {
        val usernameRegex = Regex("^[a-zA-Z0-9.,'\\-?]+\$")
        return username.matches(usernameRegex)
    }

     fun doesUsernameExist(username: String): Boolean {
        return repository.findByUserName(username) != null
    }

     fun doesEmailExist(email: String): Boolean {
        return repository.findByEmail(email) != null
    }

     fun getLoggedInUser(request: HttpServletRequest): User? {
        return request.session.getAttribute("user") as? User
    }

     fun updateUser(updatedUser: User): User {
        val existingUser = repository.findById(updatedUser.id)
            .orElseThrow { com.sorsix.finalproject.easyeats.models.exception.EmailNotFoundException() }

        existingUser.first_name = updatedUser.first_name
        existingUser.last_name = updatedUser.last_name
        existingUser.email = updatedUser.email
        existingUser.passw = updatedUser.password
        existingUser.image = updatedUser.image
        existingUser.userName = updatedUser.username

        return repository.save(existingUser)
    }

     fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByUserName(username)
        if (user == null) {
            throw com.sorsix.finalproject.easyeats.models.exception.EmailNotFoundException()
        }
        val authorities = mutableListOf<SimpleGrantedAuthority>()

        if (user != null) {
            authorities.add(SimpleGrantedAuthority("ROLE_${user.role.name}"))
        }

        return org.springframework.security.core.userdetails.User(
            user?.username,
            user?.password,
            authorities
        )
    }




private fun generateRandomImageName(): String {
    val sb = StringBuilder()
    for (i in 0..5) {
        val rand = listOf(('a'..'z'), ('A'..'Z')).flatten().random()
        sb.append(rand)
    }
    return sb.toString()
}
}