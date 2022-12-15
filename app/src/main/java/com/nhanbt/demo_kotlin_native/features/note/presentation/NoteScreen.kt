package com.nhanbt.demo_kotlin_native.features.note.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nhanbt.demo_kotlin_native.config.AppRoute
import com.nhanbt.demo_kotlin_native.core.components.TopBar
import com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components.NotesContent
import com.nhanbt.demo_kotlin_native.features.note.presentation.notes.components.NotesWrapper

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NotesScreen(
    navController: NavController,
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar()
        },
        content = { padding ->
            NotesWrapper(
                content = { notes ->
                    NotesContent(
                        padding = padding,
                        notes = notes,
                        navController = navController,
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(AppRoute.AddNote.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        },
    )
}
