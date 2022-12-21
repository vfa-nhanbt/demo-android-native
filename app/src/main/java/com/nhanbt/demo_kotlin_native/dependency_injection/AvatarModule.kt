package com.nhanbt.demo_kotlin_native.dependency_injection

import com.google.firebase.storage.StorageReference
import com.nhanbt.demo_kotlin_native.features.avatar.data.datasources.AvatarDataSource
import com.nhanbt.demo_kotlin_native.features.avatar.data.datasources.AvatarDataSourceImp
import com.nhanbt.demo_kotlin_native.features.avatar.data.repositories.AvatarRepositoryImp
import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.AvatarRepository
import com.nhanbt.demo_kotlin_native.features.avatar.domain.useCases.AvatarUseCase
import com.nhanbt.demo_kotlin_native.features.avatar.domain.useCases.GetImage
import com.nhanbt.demo_kotlin_native.features.avatar.domain.useCases.UploadImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AvatarModule {
    @Provides
    fun avatarDataSource(
        storageRef: StorageReference
    ): AvatarDataSource = AvatarDataSourceImp(storageRef)

    @Provides
    fun avatarRepository(
        dataSource: AvatarDataSource
    ): AvatarRepository = AvatarRepositoryImp(dataSource)

    @Provides
    fun avatarUseCases(
        repo: AvatarRepository
    ) = AvatarUseCase(
        getImage = GetImage(repo),
        uploadImage = UploadImage(repo),
    )
}
