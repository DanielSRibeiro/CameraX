package com.example.camerax.core.domain

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.view.LifecycleCameraController
import com.example.camerax.core.data.CameraRepository
import com.example.camerax.core.domain.TakePhotoUseCase.Params
import javax.inject.Inject

interface TakePhotoUseCase {
    operator fun invoke(params: Params)

    data class Params(
        val controller: LifecycleCameraController,
        val context: Context,
        val onPhotoTaken: (Bitmap) -> Unit
    )
}

class TakePhotoUseCaseImp @Inject constructor(
    private val repository: CameraRepository
) : TakePhotoUseCase {

    override operator fun invoke(
        params: Params
    ) {
        repository.takePhoto(
            controller = params.controller,
            context = params.context,
            onPhotoTaken = params.onPhotoTaken
        )
    }
}