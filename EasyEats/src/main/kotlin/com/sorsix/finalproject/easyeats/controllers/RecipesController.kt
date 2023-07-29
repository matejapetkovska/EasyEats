package com.sorsix.finalproject.easyeats.controllers

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.service.RecipeService
import com.sorsix.finalproject.easyeats.service.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/recipes")
@CrossOrigin(origins = ["http://localhost:4200"])
class RecipesController(private val recipeService: RecipeService,
                        private val userService: UserService) {
    @GetMapping("/{category_id}")
    fun getRecipesByCategory(@PathVariable category_id: String) : List<Recipe>? {
        return recipeService.getAllRecipesByCategory(category_id)
    }

    @GetMapping("/{category_id}/{subCategory_id}")
    fun getRecipesByCategoryAndSubCategory(@PathVariable category_id: String,
                                           @PathVariable subCategory_id: String): List<Recipe>?{
        return recipeService.getAllRecipesByCategoryAndSubCategory(category_id, subCategory_id)
    }

    @GetMapping("/")
    fun getAllRecipes(): List<Recipe> {
        return recipeService.getAllRecipes()
    }

    @GetMapping("/query/{queryText}")
    fun getRecipesByTitleOrDescriptionContainingQueryText(@PathVariable queryText: String): List<Recipe>{
        return recipeService.getAllRecipesByTitleContaining(queryText)
    }

    @PostMapping("/add")
    fun addRecipe(@RequestParam title: String,
                  @RequestParam description: String,
                  @RequestParam file: MultipartFile,
                  @RequestParam category_id: String,
                  @RequestParam subCategory_id: String,
                  @RequestParam ingredients: String,
                    request: HttpServletRequest): ResponseEntity<Any>{
        val user = userService.getLoggedInUser(request)
            ?: return ResponseEntity.badRequest().body(Error("Error in saving recipe. Please log in first."))
        val recipe = recipeService.addRecipe(title, description, file, category_id, subCategory_id, ingredients, user)
            ?: return ResponseEntity.badRequest().body(Error("Error in saving recipe. Please change image name."))
        return ResponseEntity.ok(recipe)
    }
}