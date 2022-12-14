package com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.viewModel

import androidx.compose.ui.focus.FocusState
import java.time.LocalDateTime

sealed class AddNoteEvent {
    data class EnteredTitle(val value: String) : AddNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddNoteEvent()
    data class EnteredDescription(val value: String) : AddNoteEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddNoteEvent()
    data class SelectCategory(val checkboxValue: Boolean, val categoryId: String) : AddNoteEvent()
    data class SelectDateTime(val dateTime: LocalDateTime) : AddNoteEvent()
    object SaveNote : AddNoteEvent()
}
