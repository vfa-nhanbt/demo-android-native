package com.nhanbt.demo_kotlin_native.features.avatar.domain.useCases

import android.net.Uri
import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.AvatarRepository

class UploadImage(
    private val repo: AvatarRepository
) {
    suspend operator fun invoke(path: Uri) = repo.uploadImage(path)
}
