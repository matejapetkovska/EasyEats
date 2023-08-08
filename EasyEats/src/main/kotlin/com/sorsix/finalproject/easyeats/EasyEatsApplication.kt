package com.sorsix.finalproject.easyeats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.servlet.config.annotation.EnableWebMvc


@SpringBootApplication
class EasyEatsApplication {
}

fun main(args: Array<String>) {
    runApplication<EasyEatsApplication>(*args)
}
