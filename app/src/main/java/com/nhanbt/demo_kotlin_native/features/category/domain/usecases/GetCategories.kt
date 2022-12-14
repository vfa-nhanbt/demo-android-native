package com.nhanbt.demo_kotlin_native.features.category.domain.usecases

import com.nhanbt.demo_kotlin_native.features.category.domain.repository.CategoryRepository

class GetCategories(
    private val repo: CategoryRepository
) {
    operator fun invoke() = repo.getCategories()
}