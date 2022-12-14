package com.nhanbt.demo_kotlin_native.config

sealed class AppRoute(val route: String) {
    object NotesScreen : AppRoute("notes")
    object AddNote : AppRoute("add-note")
}
