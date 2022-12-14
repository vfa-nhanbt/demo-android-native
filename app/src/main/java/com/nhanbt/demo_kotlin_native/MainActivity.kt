package com.nhanbt.demo_kotlin_native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nhanbt.demo_kotlin_native.config.AppRoute
import com.nhanbt.demo_kotlin_native.features.note.presentation.NotesScreen
import com.nhanbt.demo_kotlin_native.features.note.presentation.addNote.AddNoteScreen
import com.nhanbt.demo_kotlin_native.ui.theme.Demo_kotlin_nativeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Demo_kotlin_nativeTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Black,
                        darkIcons = false
                    )
                }

                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = AppRoute.NotesScreen.route
                    ) {
                        composable(route = AppRoute.NotesScreen.route) {
                            NotesScreen(navController = navController)
                        }
                        composable(
                            route = AppRoute.AddNote.route + "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId",
                                ) {
                                    type = NavType.StringType
                                    defaultValue = "N/A"
                                }
                            )
                        ) {
                            AddNoteScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
