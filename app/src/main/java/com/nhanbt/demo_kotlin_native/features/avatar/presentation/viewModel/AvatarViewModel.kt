package com.nhanbt.demo_kotlin_native.features.avatar.presentation.viewModel

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhanbt.demo_kotlin_native.BuildConfig
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.avatar.domain.repositories.ImageUrlResponse
import com.nhanbt.demo_kotlin_native.features.avatar.domain.useCases.AvatarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class AvatarViewModel @Inject constructor(
    private val useCase: AvatarUseCase
) : ViewModel() {
    var avatarResponse by mutableStateOf<ImageUrlResponse>(BaseResponse.Loading)
        private set

    init {
        getImageUrl()
    }

    private fun getImageUrl() = viewModelScope.launch {
        useCase.getImage().also {
            avatarResponse = it
        }
    }

    fun uploadImage(path: Uri) = viewModelScope.launch {
        useCase.uploadImage(path).also {
            avatarResponse = it
        }
    }

    fun getUriFromFileProvider(context: Context): Uri? {
        val imageDirectory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return if (imageDirectory != null) {
            FileProvider.getUriForFile(
                context,
                BuildConfig.APPLICATION_ID + ".provider",
                File(imageDirectory.path, "user-avatar")
            )
        } else {
            null
        }
    }
}
