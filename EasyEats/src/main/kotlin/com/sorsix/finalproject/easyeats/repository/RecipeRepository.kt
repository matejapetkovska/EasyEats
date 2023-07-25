package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.Category
import com.sorsix.finalproject.easyeats.models.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository: JpaRepository<Recipe, Long> {

    fun findByCategory(category: Category): List<Recipe>?

}