package com.sildeag.sound2text.core.pdf.render

import androidx.compose.ui.graphics.ImageBitmap
interface PdfRenderer {
    suspend fun render(pageIndex: Int): RenderedPage
}
data class RenderedPage(
    val bitmap: ImageBitmap,
    val width: Int,
    val height: Int
)