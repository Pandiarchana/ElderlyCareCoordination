package com.example.elderlycarecoordination.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val GreenPrimary = Color(0xFF3A8667)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    onPrimary = White,
    background = Black,
    onBackground = White
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = White,
    background = White,
    onBackground = Black
)

@Composable
fun ElderlyCareCoordinationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
