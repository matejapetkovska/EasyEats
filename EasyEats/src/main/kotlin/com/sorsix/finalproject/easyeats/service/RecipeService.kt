package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.models.User
import org.springframework.web.multipart.MultipartFile


interface RecipeService {

    fun getRecipeById(recipe_id: String): Recipe?
    fun getAllRecipesByCategory(category_id: String): List<Recipe>?

    fun getAllRecipesByCategoryAndSubCategory(category_id: String, subCategory_id: String): List<Recipe>?

    fun getAllRecipes(): List<Recipe>

    fun getAllRecipesByTitleContaining(queryText: String): List<Recipe>

    fun addRecipe(
        title: String,
        description: String,
        file: MultipartFile,
        category_id: String,
        subCategory_id: String,
        ingredients: String,
        user: User
    ): Recipe?

    fun editRecipe(
        recipe_id: String,
        title: String,
        description: String,
        category_id: String,
        subCategory_id: String,
        ingredients: String,
        user: User?
    ): Recipe?

    fun getAllRecipesByUser(used_id: String): List<Recipe>

    fun deleteRecipe(recipe_id: Long)

}