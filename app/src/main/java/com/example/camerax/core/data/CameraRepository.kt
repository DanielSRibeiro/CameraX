package com.example.camerax.core.data

import android.content.Context
import android.graphics.Bitmap
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import com.example.camerax.utils.rotateBitmap
import javax.inject.Inject

interface CameraRepository {
    fun takePhoto(
        controller: LifecycleCameraController,
        context: Context,
        onPhotoTaken: (Bitmap) -> Unit
    )
}


class CameraRepositoryImpl @Inject constructor() : CameraRepository {

    override fun takePhoto(
        controller: LifecycleCameraController,
        context: Context,
        onPhotoTaken: (Bitmap) -> Unit
    ) {
        controller.takePicture(
            ContextCompat.getMainExecutor(
                context
            ),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    val correctedBitmap = image
                        .toBitmap()
                        .rotateBitmap(image.imageInfo.rotationDegrees)
                    onPhotoTaken(correctedBitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    exception.printStackTrace()
                }

            }
        )
    }
}