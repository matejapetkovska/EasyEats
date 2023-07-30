package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.models.Review
import com.sorsix.finalproject.easyeats.models.User

interface ReviewService {
    fun getReviewById(recipe_id: String): List<Review>?
    fun addReviewForRecipe(comment: String, rating: Int, user: User, recipe: Recipe): Review
}