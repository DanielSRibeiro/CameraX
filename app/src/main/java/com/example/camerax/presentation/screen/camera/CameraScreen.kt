package com.example.camerax.presentation.screen.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.camerax.presentation.components.commo.PhotoBottomSheetContent
import com.example.camerax.presentation.screen.camera.state.CameraState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraScreen(
    uiSate: CameraState,
    onPhotoTaken: (
        controller: LifecycleCameraController,
        context: Context,
    ) -> Unit,
    onShowLastPhotoClick: () -> Unit,
    navController: NavController
) {
    val bitmaps = uiSate.bitmaps

    LaunchedEffect(bitmaps.lastOrNull()) {
        if (bitmaps.isNotEmpty()) {
            onShowLastPhotoClick()
            delay(3000)
            onShowLastPhotoClick()
        }
    }

    val controller = remember {
        LifecycleCameraController(navController.context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                        CameraController.VIDEO_CAPTURE
            )
        }
    }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            PhotoBottomSheetContent(
                bitmap = bitmaps,
                modifier = Modifier.fillMaxWidth()
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = scaffoldState.snackbarHostState)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CameraPreview(
                controller = controller,
                modifier = Modifier.fillMaxSize()
            )

            IconButton(
                modifier = Modifier.offset(16.dp, 16.dp),
                onClick = { toggleCamera(controller) }
            ) {
                Icon(
                    imageVector = Icons.Default.Cameraswitch,
                    contentDescription = "trocar a camera"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {

                AnimatedVisibility(uiSate.showLastPhoto) {
                    Card(
                        modifier = Modifier
                            .height(128.dp)
                            .width(128.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Image(
                            bitmap = bitmaps.last().asImageBitmap(),
                            contentDescription = "Ultima foto tirada",
                            contentScale = ContentScale.Crop,
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Photo,
                            contentDescription = "Abrir a galeria de fotos salvas"
                        )
                    }

                    IconButton(
                        onClick = {
                            onPhotoTaken(controller, navController.context)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Tirar foto"
                        )
                    }
                }
            }
        }
    }
}

private fun toggleCamera(controller: LifecycleCameraController) {
    controller.cameraSelector =
        if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
            CameraSelector.DEFAULT_FRONT_CAMERA
        } else {
            CameraSelector.DEFAULT_BACK_CAMERA
        }
}