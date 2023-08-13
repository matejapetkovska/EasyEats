package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.models.dto.RecipeReviewDto
import com.sorsix.finalproject.easyeats.service.RecipeDetailsService
import com.sorsix.finalproject.easyeats.service.ReviewService
import com.sorsix.finalproject.easyeats.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = ["http://localhost:4200"])
class RecipeDetailsController(
    private val recipeDetailsService: RecipeDetailsService,
    private val reviewService: ReviewService,
    private val userService: UserService
) {

    @GetMapping("/{recipe_id}")
    fun getRecipeWithReview(@PathVariable recipe_id: String): RecipeReviewDto? {
        val recipe = recipeDetailsService.getRecipeById(recipe_id)
        if (recipe != null) {
            val reviews = reviewService.getReviewById(recipe_id)
            return RecipeReviewDto(recipe, reviews)
        }
        return null
    }

    @GetMapping("/recipeDetails/{recipe_id}")
    fun getRecipeDetails(@PathVariable recipe_id: String): Recipe? {
        return this.recipeDetailsService.getRecipeById(recipe_id)
    }

    @PostMapping("/{recipe_id}")
    fun addReview(
        @PathVariable recipe_id: String,
        @RequestParam comment: String,
        @RequestParam rating: Int,
        @RequestParam user_id: Long
    ): ResponseEntity<Any> {
        val user = userService.getUserFromId(user_id)
            ?: return ResponseEntity.badRequest().body(Error("Error in saving review. Please log in first."))
        val recipe = recipeDetailsService.getRecipeById(recipe_id)
        if (recipe != null) {
            val review = reviewService.addReviewForRecipe(comment, rating, user, recipe)
            return ResponseEntity.ok(review)
        }
        return ResponseEntity.badRequest().body(Error("No recipe found."))
    }
}
