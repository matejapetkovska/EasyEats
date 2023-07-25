package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.Recipe
import com.sorsix.finalproject.easyeats.repository.RecipeRepository
import com.sorsix.finalproject.easyeats.service.RecipeService
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl(private val recipeRepository: RecipeRepository): RecipeService {
    override fun getAllRecipesByCategory(category_id: String): List<Recipe>? {
        val categoryIdLong=category_id.toLongOrNull()
        if(categoryIdLong != null){
            return recipeRepository.findByCategory_Id(categoryIdLong)
        }
        return null
    }

    override fun getAllRecipesByCategoryAndSubCategory(category_id: String,
                                                       subCategory_id: String): List<Recipe>? {
        val categoryIdLong=category_id.toLongOrNull()
        val subCategoryIdLong=subCategory_id.toLongOrNull()
        if(categoryIdLong != null && subCategoryIdLong != null){
            return recipeRepository.findByCategory_IdAndSubCategory_Id(categoryIdLong, subCategoryIdLong)
        }
        return null
    }
}