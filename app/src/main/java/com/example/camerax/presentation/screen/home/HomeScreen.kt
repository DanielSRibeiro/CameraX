package com.example.camerax.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    navigateToCamera: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = navigateToCamera,
            modifier = Modifier
                .padding(16.dp),
        ) {
            Row {
                Text(
                    text = "Ir para tela de câmera",
                    fontSize = 16.sp,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .padding(end = 8.dp)
                )

                Icon(
                    imageVector = Icons.Default.Cameraswitch,
                    contentDescription = "Ir para tela de câmera",
                    tint = Color.DarkGray
                )
            }
        }
    }
}