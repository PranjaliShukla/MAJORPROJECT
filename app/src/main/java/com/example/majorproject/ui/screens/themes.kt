package com.example.majorproject.ui.screens



import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = Color(0xFF2F6BFF),
    background = Color(0xFF0B0B0B),
    surface = Color(0xFF101012),
    onPrimary = Color.White,
    onBackground = Color.White,
    onSurface = Color(0xFFBFC7D2)
)

@Composable
fun DeepShieldTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = Typography(),
        content = content
    )
}
