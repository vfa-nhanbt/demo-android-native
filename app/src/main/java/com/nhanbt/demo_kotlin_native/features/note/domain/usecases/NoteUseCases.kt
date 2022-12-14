package com.nhanbt.demo_kotlin_native.features.note.domain.usecases

data class NoteUseCases(
    val getNotes: GetNotes,
    val addNote: AddNote,
    val deleteNote: DeleteNote,
    val getNoteById: GetNoteById,
    val updateNoteById: UpdateNoteById,
)
