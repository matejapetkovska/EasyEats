package com.sorsix.finalproject.easyeats.controllers

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/logout")
class LogoutController {

    @GetMapping
    fun logout(request:HttpServletRequest): RedirectView {
        request.session.invalidate()
        return RedirectView("/api/login")
    }
}