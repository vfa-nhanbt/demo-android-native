package com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nhanbt.demo_kotlin_native.config.AppRoute
import com.nhanbt.demo_kotlin_native.features.note.domain.repositories.Notes
import com.nhanbt.demo_kotlin_native.features.note.presentation.notes.viewModel.NotesViewModel

@Composable
fun NotesContent(
    padding: PaddingValues,
    notes: Notes,
    viewModel: NotesViewModel = hiltViewModel(),
    navController: NavController,
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
                        navController.navigate(AppRoute.AddNote.route + "?noteId=${note.id}")
                    },
                onDelete = {
                    viewModel.deleteNote(note.id)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
