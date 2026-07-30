package com.sildeag.sound2text.uicommon.pdf

import androidx.compose.ui.graphics.ImageBitmap

data class UiPdfPage(
    val index: Int,
    val text: String,
    val bitmap: Any,    // Platform-specific bitmap (Android: Bitmap, Desktop: BufferedImage)
    val width: Int,
    val height: Int
)

data class UiPdfDocument(
    val pages: List<UiPdfPage>
)
