package com.example.camerax.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.camerax.ui.theme.CameraXTheme
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.camerax.presentation.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!requestCameraPermissions()) {
            ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSIONS.toTypedArray(),
                0
            )
        }
        setContent {
            CameraXTheme {
                NavigationGraph(navController = rememberNavController())
            }
        }
    }

    private fun requestCameraPermissions(): Boolean {
        return CAMERA_PERMISSIONS.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERA_PERMISSIONS = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    }
}