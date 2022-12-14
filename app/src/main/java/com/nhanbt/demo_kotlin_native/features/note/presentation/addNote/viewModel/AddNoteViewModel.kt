package com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.note.domain.model.Note
import com.nhanbt.demo_kotlin_native.features.note.domain.usecases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val useCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var currentNoteId: String? = null

    private val _noteTitle = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter title..."
        )
    )
    val noteTitle: State<NoteTextFieldState> = _noteTitle

    private val _noteDescription = mutableStateOf(
        NoteTextFieldState(
            hint = "Enter description for this note..."
        )
    )
    val noteDescription: State<NoteTextFieldState> = _noteDescription

    private val _noteCategories = mutableListOf<String>()

    private var _deadline = LocalDateTime.now().plusDays(1)

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("noteId")?.let { noteId ->
            if (noteId.compareTo(noteId) != 0) {
                viewModelScope.launch {
                    useCases.getNoteById(noteId).also { note ->
                        when (note) {
                            is BaseResponse.Success -> currentNoteId = note.data.id
                            is BaseResponse.Failure -> currentNoteId = note.e.toString()
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: AddNoteEvent) {
        when (event) {
            is AddNoteEvent.EnteredTitle -> {
                _noteTitle.value = noteTitle.value.copy(
                    text = event.value
                )
            }
            is AddNoteEvent.ChangeTitleFocus -> {
                _noteTitle.value = noteTitle.value.copy(
                    isHintVisible = ! event.focusState.isFocused &&
                            noteTitle.value.text.isBlank()
                )
            }
            is AddNoteEvent.EnteredDescription -> {
                _noteDescription.value = _noteDescription.value.copy(
                    text = event.value
                )
            }
            is AddNoteEvent.ChangeDescriptionFocus -> {
                _noteDescription.value = _noteDescription.value.copy(
                    isHintVisible = ! event.focusState.isFocused &&
                            _noteDescription.value.text.isBlank()
                )
            }
            is AddNoteEvent.SelectCategory -> {
                if (event.checkboxValue) {
                    _noteCategories.add(event.categoryId)
                } else {
                    _noteCategories.remove(event.categoryId)
                }
            }
            is AddNoteEvent.SelectDateTime -> {
                _deadline = event.dateTime
            }
            is AddNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        val note = Note(
                            title = _noteTitle.value.text,
                            description = _noteDescription.value.text,
                            categories = _noteCategories,
                            deadline = Timestamp(
                                Date.from(
                                    _deadline.atZone(ZoneId.systemDefault()).toInstant()
                                )
                            ),
                        )
                        useCases.addNote(note)
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveNote : UiEvent()
    }
}
