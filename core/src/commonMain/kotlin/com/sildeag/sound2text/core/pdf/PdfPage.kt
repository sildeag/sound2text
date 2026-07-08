package com.sildeag.sound2text.core.pdf

import androidx.compose.ui.graphics.ImageBitmap

data class PdfPage(
    val pageNumber: Int,
    val index: Int,
    val bitmap: ImageBitmap?,
    val text: String
)
