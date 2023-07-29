package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.models.User
import org.springframework.web.multipart.MultipartFile


interface RecipeService {
    fun getAllRecipesByCategory(category_id: String): List<Recipe>?

    fun getAllRecipesByCategoryAndSubCategory(category_id: String, subCategory_id: String): List<Recipe>?

    fun getAllRecipes(): List<Recipe>

    fun getAllRecipesByTitleContaining(queryText: String): List<Recipe>

    fun addRecipe(title: String, description: String, file: MultipartFile, category_id: String, subCategory_id: String, ingredients: String, user: User): Recipe?
}