package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Review

interface ReviewService {
    fun getReviewById(recipe_id: String): List<Review>?
}