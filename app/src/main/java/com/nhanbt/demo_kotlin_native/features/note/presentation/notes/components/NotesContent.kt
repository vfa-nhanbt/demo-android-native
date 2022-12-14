package com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.Notes
import com.nhanbt.demo_kotlin_native.features.note.presentation.notes.viewModel.NotesViewModel

@Composable
fun NotesContent(
    padding: PaddingValues,
    notes: Notes,
    viewModel: NotesViewModel = hiltViewModel(),
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(notes) { note ->
            NoteItem(
                note = note,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        Log.d("FROM_NOTES_CONTENT", "note ${note.id} clicked!!")
                    },
                onDelete = {
                    viewModel.deleteNote(note.id)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
