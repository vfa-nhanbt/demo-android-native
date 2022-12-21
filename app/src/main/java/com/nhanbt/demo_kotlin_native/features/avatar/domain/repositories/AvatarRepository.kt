package com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories

import android.net.Uri
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse

typealias ImageUrlResponse = BaseResponse<String>

interface AvatarRepository {
    suspend fun getImageUrl(): ImageUrlResponse

    suspend fun uploadImage(path: Uri): ImageUrlResponse
}
