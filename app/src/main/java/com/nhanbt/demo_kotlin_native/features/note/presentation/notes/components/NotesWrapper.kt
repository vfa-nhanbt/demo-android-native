package com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhanbt.demo_kotlin_native.core.components.ProgressBar
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.Notes
import com.nhanbt.demo_kotlin_native.features.note.presentation.notes.viewModel.NotesViewModel

@Composable
fun NotesWrapper(
    viewModel: NotesViewModel = hiltViewModel(),
    content: @Composable (notes: Notes) -> Unit
) {
    when (val response = viewModel.notesResponse) {
        is BaseResponse.Loading -> ProgressBar()
        is BaseResponse.Success -> content(response.data)
        is BaseResponse.Failure -> print(response.e)
    }
}
