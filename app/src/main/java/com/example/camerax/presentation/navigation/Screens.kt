package com.example.camerax.presentation.navigation

sealed class Screens(val route: String) {
    data object HomeScreen : Screens(route = "home_screen")
    data object CameraScreen : Screens(route = "camera_screen")
}