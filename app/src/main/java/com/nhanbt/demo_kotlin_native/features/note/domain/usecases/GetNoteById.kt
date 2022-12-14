package com.nhanbt.demo_kotlin_native.features.note.domain.usecases

import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.NotesRepository

class GetNoteById(
    private val repo: NotesRepository
) {
    suspend operator fun invoke(noteId: String) = repo.getNoteById(noteId)
}
