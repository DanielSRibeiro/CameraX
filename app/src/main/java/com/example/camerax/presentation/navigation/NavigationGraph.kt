package com.example.camerax.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.camerax.presentation.screen.camera.CameraScreen
import com.example.camerax.presentation.screen.camera.CameraViewModel
import com.example.camerax.presentation.screen.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
) {
    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {

            NavHost(
                navController = navController,
                startDestination = Screens.HomeScreen.route
            ) {
                composable(Screens.HomeScreen.route) {
                    HomeScreen(
                        navigateToCamera = {
                            navController.navigate(Screens.CameraScreen.route)
                        }
                    )
                }
                composable(Screens.CameraScreen.route) {
                    val viewModel: CameraViewModel = hiltViewModel()
                    val uiState = viewModel.uiState
                    val onTakePicture = viewModel::onTakePhotoClick
                    val onShowLastPhotoClick = viewModel::onShowLastPhotoClick

                    CameraScreen(
                        uiSate = uiState,
                        onPhotoTaken = onTakePicture,
                        onShowLastPhotoClick = onShowLastPhotoClick,
                        navController = navController
                    )
                }
            }
        }
    }
}