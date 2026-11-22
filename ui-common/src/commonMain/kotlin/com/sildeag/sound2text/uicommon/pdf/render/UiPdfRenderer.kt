package com.sildeag.sound2text.uicommon.pdf.render

import androidx.compose.ui.graphics.ImageBitmap

interface UiPdfPageRenderer {
    suspend fun renderBitmapForPage(bytes: ByteArray, index: Int): ImageBitmap?
}