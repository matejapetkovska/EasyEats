package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Category
import com.sorsix.finalproject.easyeats.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoriesController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(): ResponseEntity<List<Category>>{
        return ResponseEntity.ok(categoryService.getAllCategories())
    }

    @GetMapping("/{category_id}")
    fun getCategoryById(@PathVariable category_id: Long): Category?{
        return categoryService.getCategoryById(category_id)
    }
}