package com.sorsix.finalproject.easyeats.service.implementations

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sorsix.finalproject.easyeats.models.Ingredient
import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.models.User
import com.sorsix.finalproject.easyeats.models.dto.IngredientDto
import com.sorsix.finalproject.easyeats.repository.IngredientRepository
import com.sorsix.finalproject.easyeats.repository.RecipeRepository
import com.sorsix.finalproject.easyeats.service.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import kotlin.io.path.exists

@Service
class RecipeServiceImpl(
    private val recipeRepository: RecipeRepository,
    private val categoryService: CategoryService,
    private val subCategoryService: SubCategoryService,
    private val ingredientService: IngredientService,
    private val ingredientRepository: IngredientRepository,
    private val reviewService: ReviewService
) : RecipeService {

    override fun getRecipeById(recipe_id: String): Recipe? {
        val recipeIdLong = recipe_id.toLongOrNull()
        return recipeIdLong?.let { this.recipeRepository.findRecipeById(it) }
    }
    override fun getAllRecipesByCategory(category_id: String): List<Recipe>? {
        val categoryIdLong = category_id.toLongOrNull()
        if (categoryIdLong != null) {
            return recipeRepository.findByCategory_Id(categoryIdLong)
        }
        return null
    }

    override fun getAllRecipesByCategoryAndSubCategory(
        category_id: String,
        subCategory_id: String
    ): List<Recipe>? {
        val categoryIdLong = category_id.toLongOrNull()
        val subCategoryIdLong = subCategory_id.toLongOrNull()
        if (categoryIdLong != null && subCategoryIdLong != null) {
            return recipeRepository.findByCategory_IdAndSubCategory_Id(categoryIdLong, subCategoryIdLong)
        }
        return null
    }

    override fun getAllRecipes(): List<Recipe> {
        return recipeRepository.findAll()
    }

    override fun getAllRecipesByTitleContaining(queryText: String): List<Recipe> {
        return recipeRepository.findByTitleContainingIgnoreCase(queryText)
    }

    private fun generateRandomImageName(): String {
        val sb = StringBuilder()
        for (i in 0..5) {
            val rand = listOf(('a'..'z'), ('A'..'Z')).flatten().random()
            sb.append(rand)
        }
        return sb.toString()
    }

    override fun addRecipe(
        title: String,
        description: String,
        file: MultipartFile,
        category_id: String,
        subCategory_id: String,
        ingredients: String,
        user: User
    ): Recipe? {
        val category = categoryService.getCategoryById(category_id.toLong())
        val subCategory = subCategoryService.getSubCategoryById(subCategory_id.toLong())
        var imageName = generateRandomImageName() + ".jpg"
        var imageFilePath = Paths.get("src\\main\\Frontend\\EasyEats\\src\\assets\\recipe_images\\", imageName)
        while (imageFilePath.exists()) {
            imageName = generateRandomImageName()
            imageFilePath = Paths.get("src\\main\\Frontend\\EasyEats\\src\\assets\\recipe_images\\", imageName)
        }
        Files.copy(file.inputStream, Paths.get(imageFilePath.toString()))
        val recipe = Recipe(
            0, title, description,
            mutableListOf(), imageName, LocalDateTime.now(),
            category, subCategory, user
        )
        val objectMapper = jacksonObjectMapper()
        val ingredientsList: List<IngredientDto> = objectMapper.readValue(
            ingredients,
            objectMapper.typeFactory.constructCollectionType(List::class.java, IngredientDto::class.java)
        )
        for (ingredient in ingredientsList) {
            val savedIngredient =
                ingredientService.saveIngredient(ingredient.name, ingredient.quantity, ingredient.measurementUnit)
            recipe.ingredients.add(savedIngredient)
        }
        return recipeRepository.save(recipe)
    }

    override fun editRecipe(
        recipe_id: String,
        title: String,
        description: String,
        category_id: String,
        subCategory_id: String,
        ingredients: String,
        user: User?
    ): Recipe? {
        val recipeIdLong = recipe_id.toLongOrNull()
        var recipe = recipeIdLong?.let { recipeRepository.findById(it) }
        if (recipe != null) {
            recipe.get().title = title
            recipe.get().description = description

            val category = category_id.toLongOrNull()?.let { categoryService.getCategoryById(it) }
            recipe.get().category = category
            val subCategory = subCategory_id.toLongOrNull()?.let { subCategoryService.getSubCategoryById(it) }
            recipe.get().subCategory = subCategory

            val objectMapper = jacksonObjectMapper()
            val ingredientsList: List<Ingredient> = objectMapper.readValue(
                ingredients,
                objectMapper.typeFactory.constructCollectionType(List::class.java, Ingredient::class.java)
            )

            recipe.get().ingredients.clear()

            for (ingredient in ingredientsList) {
                val existingIngredient = ingredientRepository.getIngredientById(ingredient.id)
                val savedIngredient: Ingredient
                if (existingIngredient != null) {
                    savedIngredient = existingIngredient
                } else {
                    savedIngredient = ingredientService.saveIngredient(
                        ingredient.name,
                        ingredient.quantity,
                        ingredient.measurementUnit
                    )
                }
                recipe.get().ingredients.add(savedIngredient)
            }

            recipeRepository.save(recipe.get())
            return recipe.get()
        }
        return null
    }

    override fun getAllRecipesByUser(used_id: String): List<Recipe> {
        val id = used_id.toLong()
        return recipeRepository.findByUser_Id(id)
    }

    @Transactional
    override fun deleteRecipe(recipe_id: Long) {
        reviewService.deleteAllByRecipeId(recipe_id)
        val recipe = recipeRepository.findById(recipe_id).get()
        val file = File("src\\main\\Frontend\\EasyEats\\src\\assets\\recipe_images\\" + recipe.image)
        file.delete()
        recipeRepository.deleteById(recipe_id)
    }

}