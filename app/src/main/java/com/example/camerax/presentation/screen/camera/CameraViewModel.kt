package com.example.camerax.presentation.screen.camera

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.camerax.core.domain.TakePhotoUseCase
import com.example.camerax.presentation.screen.camera.state.CameraState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val takePhotoUseCase: TakePhotoUseCase
) : ViewModel() {

    var uiState by mutableStateOf(CameraState())
        private set

    fun onTakePhotoClick(
        controller: LifecycleCameraController,
        context: Context,
    ) {
        takePhotoUseCase.invoke(
            params = TakePhotoUseCase.Params(
                controller = controller,
                context = context,
                onPhotoTaken = { bitmap ->
                    val newList = uiState.bitmaps.toMutableList()
                    newList.add(bitmap)
                    uiState = uiState.copy(bitmaps = newList)
                }
            )
        )
    }

    fun onShowLastPhotoClick() {
        uiState = uiState.copy(showLastPhoto = !uiState.showLastPhoto)
    }
}