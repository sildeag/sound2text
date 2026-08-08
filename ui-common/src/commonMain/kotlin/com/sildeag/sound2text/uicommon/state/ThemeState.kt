package com.sildeag.sound2text.uicommon.state

data class ThemeState(
    val isDarkMode: Boolean = false,
    val accentColor: Long = 0xFF448AFF,   // default blue
    val fontScale: Float = 1.0f
)
