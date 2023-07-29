package com.sorsix.finalproject.easyeats.models.dto

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.models.Review
import lombok.Data

@Data
class RecipeReviewDto(val recipe: Recipe, val reviews: List<Review>?)