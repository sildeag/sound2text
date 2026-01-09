package com.sildeag.sound2text.ui
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
@Composable
fun SharedTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}