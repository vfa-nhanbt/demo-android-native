package com.nhanbt.demo_kotlin_native.features.category.data.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.category.domain.entities.Category
import com.nhanbt.demo_kotlin_native.features.category.domain.repository.CategoriesResponse
import com.nhanbt.demo_kotlin_native.features.category.domain.repository.CategoryRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImp @Inject constructor(
    private val database: FirebaseFirestore
) : CategoryRepository {

    override fun getCategories(): Flow<CategoriesResponse> = callbackFlow {
        val snapshotListener =
            database.collection("categories").addSnapshotListener { snapshot, error ->
                val categoriesResponse = if (snapshot != null) {
                    val categories = mutableListOf<Category>()
                    for (doc: QueryDocumentSnapshot in snapshot) {
                        var cate: Category = doc.toObject<Category>()
                        cate.id = doc.id
                        categories.add(cate)
                    }
                    BaseResponse.Success(categories)
                } else {
                    BaseResponse.Failure(error)
                }
                trySend(categoriesResponse)
            }
        awaitClose {
            snapshotListener.remove()
        }
    }
}
