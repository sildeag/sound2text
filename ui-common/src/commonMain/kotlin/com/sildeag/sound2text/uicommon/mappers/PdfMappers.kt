package com.sildeag.sound2text.uicommon.mappers

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.uicommon.models.PdfPage
import com.sildeag.sound2text.uicommon.models.UiPdfPage
fun PdfPage.toUi(
    bitmap: ImageBitmap,
    width: Int,
    height: Int
): UiPdfPage =
    UiPdfPage(
        index = index,
        text = text,
        bitmap = bitmap,
        width = width,
        height = height
    )