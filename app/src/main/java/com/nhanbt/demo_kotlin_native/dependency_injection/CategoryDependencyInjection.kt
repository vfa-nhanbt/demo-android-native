package com.nhanbt.demo_kotlin_native.dependency_injection


import com.google.firebase.firestore.FirebaseFirestore
import com.nhanbt.demo_kotlin_native.features.category.data.datasource.CategoryRepositoryImp
import com.nhanbt.demo_kotlin_native.features.category.domain.repository.CategoryRepository
import com.nhanbt.demo_kotlin_native.features.category.domain.usecases.CategoryUseCases
import com.nhanbt.demo_kotlin_native.features.category.domain.usecases.GetCategories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CategoryDependencyInjection {
    @Provides
    fun provideCategoryRepository(
        db: FirebaseFirestore
    ): CategoryRepository = CategoryRepositoryImp(db)

    @Provides
    fun provideCategoryUseCase(
        repo: CategoryRepository
    ) = CategoryUseCases(
        getCategories = GetCategories(repo)
    )
}