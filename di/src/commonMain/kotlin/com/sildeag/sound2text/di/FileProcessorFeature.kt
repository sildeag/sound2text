package com.sildeag.sound2text.di

import androidx.compose.runtime.Composable
/**
 * Contract for a loosely-coupled feature that can process files (.wac).
 */
interface FileProcessorFeature {
    val id: String
    val title: String
    val content: @Composable () -> Unit
    val route: String get() = id
}
