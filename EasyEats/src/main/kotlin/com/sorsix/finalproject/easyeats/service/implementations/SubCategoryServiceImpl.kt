package com.sorsix.finalproject.easyeats.service.implementations

import com.sorsix.finalproject.easyeats.models.SubCategory
import com.sorsix.finalproject.easyeats.repository.SubCategoryRepository
import com.sorsix.finalproject.easyeats.service.SubCategoryService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SubCategoryServiceImpl(private val subCategoryRepository: SubCategoryRepository): SubCategoryService {

    override fun getAllSubcategories(): List<SubCategory> {
        return subCategoryRepository.findAll()
    }

    override fun getSubCategoryById(subCategory_id: Long): SubCategory? {
        return subCategoryRepository.findByIdOrNull(subCategory_id)
    }

}