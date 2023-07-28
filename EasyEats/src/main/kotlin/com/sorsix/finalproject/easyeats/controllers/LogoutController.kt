package com.sorsix.finalproject.easyeats.controllers

import org.springframework.web.bind.annotation.*
import jakarta.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true")
class LogoutController {

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest) {
        request.session.invalidate()
    }
}