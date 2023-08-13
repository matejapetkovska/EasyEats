package com.sorsix.finalproject.easyeats.repository

import com.sorsix.finalproject.easyeats.models.SubCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryRepository : JpaRepository<SubCategory, Long> {

}