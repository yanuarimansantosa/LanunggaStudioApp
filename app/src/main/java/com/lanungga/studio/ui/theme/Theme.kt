package com.lanungga.studio.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// 1. Skema warna Gelap (Dark Mode)
private val DarkColorScheme = darkColorScheme(
    primary = LanunggaBlue,
    secondary = LanunggaLime,
    tertiary = LanunggaSky
)

// 2. Skema warna Terang (Light Mode)
private val LightColorScheme = lightColorScheme(
    primary = LanunggaBlue,
    secondary = LanunggaLime,
    tertiary = LanunggaSky,
    background = LanunggaLightBg,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = LanunggaDark,
    onSurface = LanunggaDark
)

@Composable
fun LanunggaStudioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set ke false jika ingin warna brand selalu konsisten (tidak berubah ikut wallpaper HP)
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}