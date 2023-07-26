package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.service.RecipeService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = ["http://localhost:4200"])
class RecipesController(private val recipeService: RecipeService) {

    @GetMapping("/{category_id}")
    fun getRecipesByCategory(@PathVariable category_id: String) : List<Recipe>? {
        return recipeService.getAllRecipesByCategory(category_id)
    }

    @GetMapping("/{category_id}/{subCategory_id}")
    fun getRecipesByCategoryAndSubCategory(@PathVariable category_id: String,
                                           @PathVariable subCategory_id: String): List<Recipe>?{
        return recipeService.getAllRecipesByCategoryAndSubCategory(category_id, subCategory_id)
    }
}