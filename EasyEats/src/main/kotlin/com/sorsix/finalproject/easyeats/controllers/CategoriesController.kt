package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Category
import com.sorsix.finalproject.easyeats.service.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = ["http://localhost:4200"])
class CategoriesController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(): List<Category>{
        return categoryService.getAllCategories()
    }

    @GetMapping("/{category_id}")
    fun getCategoryById(@PathVariable category_id: Long): Category?{
        return categoryService.getCategoryById(category_id)
    }
}