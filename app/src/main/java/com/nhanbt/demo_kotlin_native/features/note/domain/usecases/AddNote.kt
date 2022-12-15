package com.nhanbt.demo_kotlin_native.features.note.domain.usecases

import com.nhanbt.demo_kotlin_native.features.note.domain.entities.Note
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.NotesRepository

class AddNote(
    private val repo: NotesRepository
) {
    suspend operator fun invoke(note: Note) = repo.addNote(note)
}
