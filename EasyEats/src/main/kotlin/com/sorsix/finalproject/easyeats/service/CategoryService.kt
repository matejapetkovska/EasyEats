package com.sorsix.finalproject.easyeats.service

import com.sorsix.finalproject.easyeats.models.Category


interface CategoryService {

    fun getAllCategories(): List<Category>
}