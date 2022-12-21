package com.nhanbt.demo_kotlin_native.features.avatar.data.datasources

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.ImageUrlResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface AvatarDataSource {
    suspend fun getImageUrl(): ImageUrlResponse

    suspend fun uploadImage(path: Uri): ImageUrlResponse
}

@Singleton
class AvatarDataSourceImp @Inject constructor(
    private val storageRef: StorageReference
) : AvatarDataSource {
    override suspend fun getImageUrl(): ImageUrlResponse {
        return try {
            val uri = storageRef.downloadUrl.await()
            return BaseResponse.Success(uri.toString())
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }

    override suspend fun uploadImage(path: Uri): ImageUrlResponse {
        return try {
            storageRef.putFile(path).await()
            val uri = storageRef.downloadUrl.await()
            return BaseResponse.Success(uri.toString())
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }
}
