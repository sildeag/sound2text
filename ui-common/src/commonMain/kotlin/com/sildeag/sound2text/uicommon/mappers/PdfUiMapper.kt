package com.sildeag.sound2text.uicommon.mappers

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.uicommon.models.UiPdfPage

interface PdfUiMapper<T> {
    fun map(core: PdfPage, bitmap: ImageBitmap, width: Int, height: Int): T
}


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
        height = height,
        pageNumber = core.pageNumber
    )

