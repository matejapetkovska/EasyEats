package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.service.RecipeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
class RecipesController(private val recipeService: RecipeService) {

    @GetMapping("/{category_id}")
    fun getRecipesByCategory(@PathVariable category_id: String) : List<Recipe>? {
        return recipeService.getAllRecipesByCategory(category_id)
    }
}