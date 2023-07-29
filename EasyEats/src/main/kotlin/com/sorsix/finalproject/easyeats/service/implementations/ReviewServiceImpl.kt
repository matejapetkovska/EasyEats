package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.Review
import com.sorsix.finalproject.easyeats.repository.ReviewRepository
import com.sorsix.finalproject.easyeats.service.ReviewService
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(private val reviewRepository: ReviewRepository): ReviewService {
    override fun getReviewById(recipe_id: String): List<Review>? {
        val recipeIdLong = recipe_id.toLongOrNull()
        return recipeIdLong?.let { this.reviewRepository.findReviewsByRecipeId(it) }
    }
}