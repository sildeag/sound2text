package com.sildeag.sound2text.uicommon.pdf

import androidx.compose.ui.graphics.ImageBitmap

data class UiPdfPage(
    val index: Int,
    val text: String,
    val bitmap: ImageBitmap?,
    val width: Int,
    val height: Int
)
