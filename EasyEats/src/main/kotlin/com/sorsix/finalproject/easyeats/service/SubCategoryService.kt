package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.SubCategory

interface SubCategoryService {

    fun getAllSubCategories(): List<SubCategory>

}