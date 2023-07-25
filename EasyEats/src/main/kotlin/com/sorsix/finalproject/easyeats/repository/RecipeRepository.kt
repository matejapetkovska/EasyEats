package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository: JpaRepository<Recipe, Long> {

    fun findByCategory_Id(category_id: Long): List<Recipe>?

    fun findByCategory_IdAndSubCategory_Id(category_id: Long, subCategory_id: Long): List<Recipe>?

}