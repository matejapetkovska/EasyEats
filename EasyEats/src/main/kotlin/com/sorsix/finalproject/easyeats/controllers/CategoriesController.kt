package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Category
import com.sorsix.finalproject.easyeats.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/home")
class CategoriesController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(): List<Category>{
        return categoryService.getAllCategories()
    }
}