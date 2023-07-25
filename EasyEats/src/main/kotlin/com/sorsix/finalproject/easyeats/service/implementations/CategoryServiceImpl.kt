package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.Category
import com.sorsix.finalproject.easyeats.repository.CategoryRepository
import com.sorsix.finalproject.easyeats.service.CategoryService
import org.springframework.stereotype.Service


@Service
class CategoryServiceImpl(private val categoryRepository: CategoryRepository): CategoryService {

    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun getCategoryById(id: Long): Category? {
        return categoryRepository.findById(id).orElse(null);
    }
}