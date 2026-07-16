package com.sildeag.sound2text.uicommon.model

import androidx.compose.ui.graphics.ImageBitmap

data class UiPdfPage(
    val index: Int,
    val bitmap: ImageBitmap,
    val width: Int,
    val height: Int
)
