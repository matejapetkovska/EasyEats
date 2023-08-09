package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.configurations.JwtService
import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.enumerations.Role
import com.sorsix.finalproject.easyeats.models.exception.InvalidArgumentsException
import com.sorsix.finalproject.easyeats.models.exception.InvalidPasswordException
import com.sorsix.finalproject.easyeats.models.exception.*
import com.sorsix.finalproject.easyeats.repository.UserRepository
import com.sorsix.finalproject.easyeats.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.security.Principal
import kotlin.io.path.exists


@Service
class UserServiceImpl(val repository: UserRepository,
                      val jwtService: JwtService, val passwordEncoder: PasswordEncoder) : UserService {

    override fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return email.matches(emailRegex)
    }

    override fun isValidUsername(username: String): Boolean {
        val usernameRegex = Regex("^[a-zA-Z0-9.,'\\-?]+\$")
        return username.matches(usernameRegex)
    }

    override fun doesUsernameExist(username: String): Boolean {
        return repository.findByUserName(username) != null
    }

    override fun doesEmailExist(email: String): Boolean {
        return repository.findByEmail(email) != null
    }

    override fun getLoggedInUser(request: HttpServletRequest): User? {
        return request.session.getAttribute("user") as? User
    }


    override fun updateUser(userId: Long, updatedUser: User, principal: Principal): User {
        val existingUser = repository.findById(userId)
            .orElseThrow { EmailNotFoundException() }

        existingUser.apply {
            first_name = updatedUser.first_name
            last_name = updatedUser.last_name
            userName=updatedUser.userName
            email = updatedUser.email
            passw = passwordEncoder.encode(updatedUser.passw)
            role=updatedUser.role
            image = updatedUser.image
        }

        return repository.save(existingUser)
    }

    override fun getUserFromToken(token: String): User? {
        return repository.findByEmail(jwtService.extractUsername(token))
    }

    override fun updateImageField(user_id: Long, image: MultipartFile): User? {
        val user = repository.findById(user_id).orElse(null)
        var imageName = generateRandomImageName() + ".jpg"
        var imageFilePath = Paths.get("src\\main\\Frontend\\EasyEats\\src\\assets\\user_images\\", imageName)
        while (imageFilePath.exists()) {
            imageName = generateRandomImageName()
            imageFilePath = Paths.get("src\\main\\Frontend\\EasyEats\\src\\assets\\user_images\\", imageName)
        }
        Files.copy(image.inputStream, Paths.get(imageFilePath.toString()))

        if(user.image != "default_profile_picture.jpg") {
            val file = File("src\\main\\Frontend\\EasyEats\\src\\assets\\user_images\\" + user.image)
            file.delete()
        }

        user.image=imageName
        return repository.save(user)
    }

    override fun getUserFromId(user_id: Long): User? {
        return repository.findById(user_id).orElse(null)
    }

    private fun generateRandomImageName(): String {
        val sb = StringBuilder()
        for (i in 0..5) {
            val rand = listOf(('a'..'z'), ('A'..'Z')).flatten().random()
            sb.append(rand)
        }
        return sb.toString()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByUserName(username)
        if (user == null) {
            throw EmailNotFoundException()
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

}



