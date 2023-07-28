package com.sorsix.finalproject.easyeats.models.dto

import lombok.Data

@Data
class IngredientDto(
    val name: String,
    val quantity: Int,
    val measurementUnit: String
) { }