package com.example.majorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.majorproject.navigation.AppNavHost

import com.example.majorproject.ui.theme.MAJORPROJECTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MAJORPROJECTTheme {
                AppNavHost()  // Navigation host for Splash → Home → Processing → Result
            }
        }
    }
}


