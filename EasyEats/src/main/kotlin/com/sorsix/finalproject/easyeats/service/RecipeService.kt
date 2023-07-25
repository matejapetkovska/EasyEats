package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Recipe


interface RecipeService {
    fun getAllRecipesByCategory(category_id: String): List<Recipe>?

    fun getAllRecipesByCategoryAndSubCategory(category_id: String, subCategory_id: String): List<Recipe>?
}