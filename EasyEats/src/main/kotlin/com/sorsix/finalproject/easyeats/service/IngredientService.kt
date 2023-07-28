package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Ingredient

interface IngredientService {

    fun saveIngredient(name: String, quantity: Int, measurementUnit: String): Ingredient

}