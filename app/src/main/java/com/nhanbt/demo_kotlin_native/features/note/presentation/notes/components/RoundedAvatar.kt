package com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.nhanbt.demo_kotlin_native.R
import com.nhanbt.demo_kotlin_native.core.components.ProgressBar
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.avatar.presentation.viewModel.AvatarViewModel

@Composable
fun RoundedAvatar(
    viewModel: AvatarViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val cameraResultLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                Log.d("FROM_ROUNDED_AVATAR", "${viewModel.getUriFromFileProvider(context)}")
                viewModel.getUriFromFileProvider(context)
                    ?.let { path -> viewModel.uploadImage(path) }
            }
        }

    when (val response = viewModel.avatarResponse) {
        is BaseResponse.Loading -> ProgressBar()
        is BaseResponse.Success -> Image(
            painter = rememberAsyncImagePainter(model = response.data),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    cameraResultLauncher.launch(viewModel.getUriFromFileProvider(context))
                },
        )
        is BaseResponse.Failure -> Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .clickable {
                    cameraResultLauncher.launch(viewModel.getUriFromFileProvider(context))
                },
        )
    }

}
