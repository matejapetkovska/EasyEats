package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.Ingredient
import com.sorsix.finalproject.easyeats.repository.IngredientRepository
import com.sorsix.finalproject.easyeats.service.IngredientService
import org.springframework.stereotype.Service

@Service
class IngredientServiceImpl(private val ingredientRepository: IngredientRepository): IngredientService {

    override fun saveIngredient(name: String, quantity: Int, measurementUnit: String): Ingredient {
        val ingredient = Ingredient(name=name, quantity=quantity, measurementUnit=measurementUnit)
        return ingredientRepository.save(ingredient)
    }

}