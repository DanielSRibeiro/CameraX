package com.example.camerax.presentation.screen.camera.state

import android.graphics.Bitmap

data class CameraState(
    var showLastPhoto: Boolean = false,
    var bitmaps: List<Bitmap> = emptyList()
)