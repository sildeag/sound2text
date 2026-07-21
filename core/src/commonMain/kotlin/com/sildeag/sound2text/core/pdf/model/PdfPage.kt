package com.sildeag.sound2text.core.pdf.model

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.serialization.Serializable

@Serializable
data class PdfPage(
    val index: Int,
    val text: String
) {
    val height: Int
    val width: Int
    val bitmap: ImageBitmap
}
