package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.repository.RecipeDetailsRepository
import com.sorsix.finalproject.easyeats.service.RecipeDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecipeDetailsServiceImpl(private val recipeDetailsRepository: RecipeDetailsRepository): RecipeDetailsService {

    override fun getRecipeById(recipe_id: String): Recipe? {
        val recipeIdLong = recipe_id.toLongOrNull()
        return recipeIdLong?.let { this.recipeDetailsRepository.findRecipeById(it) }
    }
}