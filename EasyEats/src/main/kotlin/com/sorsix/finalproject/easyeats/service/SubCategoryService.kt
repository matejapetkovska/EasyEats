package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.SubCategory

interface SubCategoryService {

    fun getAllSubcategories(): List<SubCategory>

    fun getSubCategoryById(subCategory_id: Long): SubCategory?
}