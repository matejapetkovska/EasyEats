package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.dto.RecipeReviewDto
import com.sorsix.finalproject.easyeats.service.RecipeDetailsService
import com.sorsix.finalproject.easyeats.service.ReviewService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = ["http://localhost:4200"])
class RecipeDetailsController(
    private val recipeDetailsService: RecipeDetailsService,
    private val reviewService: ReviewService
) {
    @GetMapping("/{recipe_id}")
    fun getRecipeDetails(@PathVariable recipe_id: String): RecipeReviewDto? {
        val recipe = recipeDetailsService.getRecipeById(recipe_id)
        if (recipe != null) {
            val reviews = reviewService.getReviewById(recipe_id)
            val recipeWithReviews = RecipeReviewDto(recipe, reviews)
            return recipeWithReviews
        }
        return null
    }
}
