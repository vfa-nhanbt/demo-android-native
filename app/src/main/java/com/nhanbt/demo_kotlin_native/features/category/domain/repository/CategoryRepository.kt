package com.nhanbt.demo_kotlin_native.features.category.domain.repository

import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.category.domain.entities.Category
import kotlinx.coroutines.flow.Flow

typealias Categories = List<Category>
typealias CategoriesResponse = BaseResponse<Categories>

interface CategoryRepository {
    fun getCategories(): Flow<CategoriesResponse>
}
