package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.repository.RecipeRepository
import com.sorsix.finalproject.easyeats.service.CategoryService
import com.sorsix.finalproject.easyeats.service.RecipeService

import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(private val categoryService: CategoryService, private val recipeRepository: RecipeRepository): RecipeService {
    override fun getAllRecipesByCategory(category_id: String): List<Recipe>? {
        val categoryIdLong=category_id.toLongOrNull()
        if(categoryIdLong != null){
            val category = categoryService.getCategoryById(categoryIdLong)
            if(category != null){
                return recipeRepository.findByCategory(category)
            }
        }
        return null
    }
}