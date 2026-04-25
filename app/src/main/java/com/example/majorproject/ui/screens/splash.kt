package com.example.majorproject.ui.screens



import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onContinue: () -> Unit) {
    val scaleTarget by remember { mutableStateOf(1f) }
    val scale by animateFloatAsState(targetValue = scaleTarget, animationSpec = tween(800))

    LaunchedEffect(Unit) {
        // wait and navigate
        delay(1500)
        onContinue()
    }

    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF0B0B0B)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(scale)
                    .background(
                        Brush.linearGradient(listOf(Color(0xFF2F6BFF), Color(0xFF3A7BFF))),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Face, contentDescription = null, tint = Color.White, modifier = Modifier.size(56.dp))
            }
            Spacer(Modifier.height(20.dp))
            Text("DeepShield – AI Deepfake Detector", color = Color(0xFFBFC7D2), fontSize = 16.sp)
        }
    }
}
