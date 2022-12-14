package com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.viewModel

data class NoteTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true
)
