package com.nhanbt.demo_kotlin_native.dependency_injection

import com.google.firebase.firestore.FirebaseFirestore
import com.nhanbt.demo_kotlin_native.features.note.data.data_source.NotesRepositoryImp
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.NotesRepository
import com.nhanbt.demo_kotlin_native.features.note.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NoteDependencyInjection {
    @Provides
    fun provideNotesRepository(
        db: FirebaseFirestore
    ): NotesRepository = NotesRepositoryImp(db)

    @Provides
    fun provideUseCase(
        repo: NotesRepository
    ) = NoteUseCases(
        getNotes = GetNotes(repo),
        addNote = AddNote(repo),
        deleteNote = DeleteNote(repo),
        getNoteById = GetNoteById(repo),
        updateNoteById = UpdateNoteById(repo)
    )
}
