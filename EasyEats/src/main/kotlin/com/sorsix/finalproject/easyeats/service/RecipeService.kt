package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Recipe


interface RecipeService {
    fun getAllRecipesByCategory(category_id: String): List<Recipe>?

}