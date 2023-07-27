package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.repository.RecipeDetailsRepository
import com.sorsix.finalproject.easyeats.service.RecipeDetailsService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipe")
@CrossOrigin(origins = ["http://localhost:4200"])
class RecipeDetailsController(private val recipeDetailsService: RecipeDetailsService) {

    @GetMapping("/{recipe_id}")
    fun getRecipeDetails(@PathVariable recipe_id: String): Recipe? {
        return recipeDetailsService.getRecipeById(recipe_id)
    }
}