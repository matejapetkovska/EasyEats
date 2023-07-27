package com.sorsix.finalproject.easyeats.configurations

import com.sorsix.finalproject.easyeats.configurations.filters.JWTAuthenticationFilter
import com.sorsix.finalproject.easyeats.configurations.filters.UrlMapping
import com.sorsix.finalproject.easyeats.service.UserService
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
class JWTWebSecurityConfig(val passwordEncoder: PasswordEncoder, val userService: UserService) {


    private var authenticationManagerInitialized = false
    private val localConfigureAuthenticationBldr: AuthenticationManagerBuilder? = null
    private var disableLocalConfigureAuthenticationBldr = false
    private val authenticationConfiguration: AuthenticationConfiguration? = null
    private var authenticationManager: AuthenticationManager? = null

    @Throws(java.lang.Exception::class)
    protected fun configure(auth: AuthenticationManagerBuilder?) {
        disableLocalConfigureAuthenticationBldr = true
    }

    @Throws(java.lang.Exception::class)
    protected fun authenticationManager(): AuthenticationManager? {
        if (!this.authenticationManagerInitialized) {
            this.configure(this.localConfigureAuthenticationBldr)
            if (this.disableLocalConfigureAuthenticationBldr) {
                this.authenticationManager = this.authenticationConfiguration?.getAuthenticationManager()
            } else {
                this.authenticationManager = this.localConfigureAuthenticationBldr?.build()
            }
            this.authenticationManagerInitialized = true
        }
        return this.authenticationManager
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(UrlMapping.AUTH + UrlMapping.SIGN_UP).permitAll()
                    .requestMatchers(UrlMapping.AUTH + UrlMapping.LOGIN).permitAll()
                    .requestMatchers(UrlMapping.VALIDATE_JWT).permitAll()
                    .requestMatchers("/recipe/{id}","/recipes","/recipes/**","/categories","/categories/{id}", "/assets/**", "/signup", "/api/**", "/subcategories").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilter(JWTAuthenticationFilter(authenticationManager(), userService, passwordEncoder))
        return http.build()
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedMethods("*")
            }
        }
    }
}
