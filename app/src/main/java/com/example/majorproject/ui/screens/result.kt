package com.example.majorproject.ui.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import coil.compose.AsyncImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    result: String,
    confidence: Float,
    imageUri: Uri?,
    onBack: () -> Unit,
    onAnalyzeAgain: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF0B0B0B)
    ) {
        Column {

            // ⭐ FIXED TOP APP BAR
            CenterAlignedTopAppBar(
                title = { Text("Analysis Result", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // RESULT LABEL COLOR
                val resultColor =
                    if (result.uppercase() == "FAKE") Color(0xFFD93A4A)
                    else Color(0xFF43C36E)

                Text(result.uppercase(), color = resultColor, fontSize = 48.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "${(confidence * 100).toInt()}% Confidence",
                    color = Color.White,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // PREVIEW CARD
                Card(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(0.9f)
                        .height(200.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF101012)),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    if (imageUri != null) {
                        AsyncImage(model = imageUri, contentDescription = null)
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Preview not available", color = Color.Gray)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ANALYZE AGAIN BUTTON
                ElevatedButton(
                    onClick = onAnalyzeAgain,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F6BFF)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Analyze Another File", color = Color.White)
                }
            }
        }
    }
}
