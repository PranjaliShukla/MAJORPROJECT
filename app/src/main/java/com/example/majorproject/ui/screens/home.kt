package com.example.majorproject.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onImageSelected: (Uri) -> Unit,
    onVideoSelected: (Uri) -> Unit
) {

    // IMAGE PICKER
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) onImageSelected(uri)
    }

    // VIDEO PICKER
    val videoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) onVideoSelected(uri)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0B0B0B)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            CenterAlignedTopAppBar(
                title = { Text("DeepShield", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF101012)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("AI Deepfake Detector", color = Color.White, fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Leverage advanced AI to verify the authenticity of images and videos.",
                        color = Color(0xFFBFC7D2),
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.padding(horizontal = 36.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {

                // IMAGE BUTTON
                ElevatedButton(
                    onClick = {
                        imagePicker.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF2F6BFF)),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Default.Face, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Select Image", color = Color.White)
                }

                // VIDEO BUTTON
                ElevatedButton(
                    onClick = {
                        videoPicker.launch("video/*")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.elevatedButtonColors(containerColor = Color(0xFF2F6BFF)),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Icon(Icons.Default.AccountBox, contentDescription = null, tint = Color.White)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("Select Video", color = Color.White)
                }
            }
        }
    }
}
