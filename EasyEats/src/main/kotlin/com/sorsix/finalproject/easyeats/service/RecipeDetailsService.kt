package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Recipe

interface RecipeDetailsService {
    fun getRecipeById(recipe_id: String): Recipe?
}