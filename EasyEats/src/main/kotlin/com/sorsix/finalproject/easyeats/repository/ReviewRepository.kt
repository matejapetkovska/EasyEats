package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository: JpaRepository<Review, Long> {
    fun findReviewsByRecipeId(recipe_id: Long): List<Review>?
}