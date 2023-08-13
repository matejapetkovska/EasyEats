package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
}