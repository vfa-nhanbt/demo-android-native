package com.nhanbt.demo_kotlin_native.features.note.presentation.notes.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.NotesResponse
import com.nhanbt.demo_kotlin_native.features.note.domain.usecases.NoteUseCases
import com.nhanbt.demo_kotlin_native.features.weather.domain.usecases.WeatherUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    private val weatherUseCases: WeatherUseCases
) : ViewModel() {
    var notesResponse by mutableStateOf<NotesResponse>(BaseResponse.Loading)
        private set

    init {
        getNotes()
    }

    private fun getNotes() = viewModelScope.launch {
        useCases.getNotes().collect { response ->
            notesResponse = response
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            useCases.deleteNote(noteId)
        }
    }
}
