package com.example.mojtrening.ui.theme.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Plava,
    secondary = Akcent,
    background = Pozadina,
    onPrimary = Color.White,
    onBackground = TekstNaslov
)

@Composable
fun MojTreningTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
