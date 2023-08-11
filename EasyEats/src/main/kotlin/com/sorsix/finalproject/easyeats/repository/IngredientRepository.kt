package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository: JpaRepository<Ingredient, Long> {
    fun getIngredientById(id: Long): Ingredient?

}