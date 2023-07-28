package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeDetailsRepository: JpaRepository<Recipe, Long> {
    fun findRecipeById(recipe_id: Long): Recipe?
}