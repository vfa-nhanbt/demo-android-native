package com.nhanbt.demo_kotlin_native.features.note.domain.usecases

import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.NotesRepository

class GetNotes(
    private val repo: NotesRepository
) {
    operator fun invoke() = repo.getNotes()
}
