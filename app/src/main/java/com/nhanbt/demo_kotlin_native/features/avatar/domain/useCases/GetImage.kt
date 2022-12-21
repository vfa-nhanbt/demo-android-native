package com.nhanbt.demo_kotlin_native.features.avatar.domain.useCases

import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.AvatarRepository

class GetImage(
    private val repo: AvatarRepository
) {
    suspend operator fun invoke() = repo.getImageUrl()
}
