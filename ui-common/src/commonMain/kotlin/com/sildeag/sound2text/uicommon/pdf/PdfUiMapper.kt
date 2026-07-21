package com.sildeag.sound2text.uicommon.pdf

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfPage

fun mapToUi(
    core: PdfPage,
    bitmap: ImageBitmap,
    width: Int,
    height: Int
): UiPdfPage =
    UiPdfPage(
        index = core.index,
        text = core.text,
        bitmap = bitmap,
        width = width,
        height = height
    )

