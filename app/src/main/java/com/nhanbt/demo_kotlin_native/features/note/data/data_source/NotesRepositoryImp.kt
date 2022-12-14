package com.nhanbt.demo_kotlin_native.features.note.data.data_source

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import com.nhanbt.demo_kotlin_native.core.response.BaseResponse
import com.nhanbt.demo_kotlin_native.features.note.domain.model.Note
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImp @Inject constructor(
    database: FirebaseFirestore
) : NotesRepository {
    private val noteReference = database.collection("notes")

    override fun getNotes(): Flow<NotesResponse> = callbackFlow {
        val snapshotListener = noteReference.addSnapshotListener { snapshot, error ->
            val notesResponse = if (snapshot != null) {
                val notes = mutableListOf<Note>()
                for (doc: QueryDocumentSnapshot in snapshot) {
                    var note: Note = doc.toObject<Note>()
                    note.id = doc.id
                    notes.add(note)
                }
                BaseResponse.Success(notes)
            } else {
                BaseResponse.Failure(error)
            }
            trySend(notesResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addNote(note: Note): AddNoteResponse {
        return try {
            val id = noteReference.document().id
            noteReference.document(id).set(note.toMap()).await()
            BaseResponse.Success(true)
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }

    override suspend fun deleteNote(noteId: String): DeleteNoteResponse {
        return try {
            noteReference.document(noteId).delete().await()
            BaseResponse.Success(true)
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }

    override suspend fun getNoteById(noteId: String): NoteResponse {
        return try {
            var note = Note()
            noteReference.document(noteId).get().addOnSuccessListener { task ->
                note = task.toObject<Note>() !!
            }.await()
            BaseResponse.Success(note)
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }

    override suspend fun updateNoteById(note: Note): UpdateNoteResponse {
        return try {
            noteReference.document(note.id).set(note.toMap()).await()
            BaseResponse.Success(true)
        } catch (e: Exception) {
            BaseResponse.Failure(e)
        }
    }
}
