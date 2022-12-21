package com.nhanbt.demo_kotlin_native

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
    
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            Log.d("APP_REQUEST_PERMISSION", "$it")
        } else {
            Log.d("APP_REQUEST_PERMISSION", "Cannot get all requested permissions!!")
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.d("APP_REQUEST_PERMISSION", "Permission granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.d("APP_REQUEST_PERMISSION", "Show camera permissions dialog!")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

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
        requestCameraPermission()
    }
}
