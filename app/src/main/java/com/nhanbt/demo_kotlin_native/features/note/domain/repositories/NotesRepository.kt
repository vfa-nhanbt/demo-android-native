package com.nhanbt.demo_kotlin_native.features.note.domain.repositories

import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.note.domain.entities.Note
import kotlinx.coroutines.flow.Flow

typealias Notes = List<Note>
typealias NotesResponse = BaseResponse<Notes>
typealias NoteResponse = BaseResponse<Note>
typealias AddNoteResponse = BaseResponse<Boolean>
typealias UpdateNoteResponse = BaseResponse<Boolean>
typealias DeleteNoteResponse = BaseResponse<Boolean>

interface NotesRepository {
    fun getNotes(): Flow<NotesResponse>

    suspend fun addNote(note: Note): AddNoteResponse

    suspend fun deleteNote(noteId: String): DeleteNoteResponse

    suspend fun getNoteById(noteId: String): NoteResponse

    suspend fun updateNoteById(note: Note): UpdateNoteResponse
}
