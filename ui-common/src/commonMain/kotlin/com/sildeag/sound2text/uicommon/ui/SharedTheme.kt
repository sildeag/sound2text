package com.sildeag.sound2text.uicommon.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sildeag.sound2text.uicommon.theme.AppShapes
import com.sildeag.sound2text.uicommon.theme.AppTypography
import com.sildeag.sound2text.uicommon.theme.Background
import com.sildeag.sound2text.uicommon.theme.Primary
import com.sildeag.sound2text.uicommon.theme.Secondary
import com.sildeag.sound2text.uicommon.theme.Surface
import com.sildeag.sound2text.uicommon.theme.TextPrimary

@Composable
fun SharedTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Primary,
            secondary = Secondary,
            background = Background,
            surface = Surface,
            onPrimary = Color.White,
            onSecondary = Color.Black,
            onBackground = TextPrimary,
            onSurface = TextPrimary
        ),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
