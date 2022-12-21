package com.nhanbt.demo_kotlin_native.features.avatar.data.repositories

import android.net.Uri
import com.nhanbt.demo_kotlin_native.features.avatar.data.datasources.AvatarDataSource
import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.AvatarRepository
import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.ImageUrlResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AvatarRepositoryImp @Inject constructor(
    private val dataSource: AvatarDataSource
) : AvatarRepository {
    override suspend fun getImageUrl(): ImageUrlResponse {
        return dataSource.getImageUrl()
    }

    override suspend fun uploadImage(path: Uri): ImageUrlResponse {
        return dataSource.uploadImage(path)
    }
}
