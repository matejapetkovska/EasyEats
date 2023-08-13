package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.SubCategory
import com.sorsix.finalproject.easyeats.service.SubCategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/subcategories")
class SubCategoryController(private val subCategoryService: SubCategoryService) {

    @GetMapping()
    fun getAllSubCategories(): List<SubCategory> {
        return subCategoryService.getAllSubcategories()
    }
}