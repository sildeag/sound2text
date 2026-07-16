package com.sildeag.sound2text.uicommon.mappers

import com.sildeag.sound2text.core.pdf.PdfPage
import com.sildeag.sound2text.uicommon.model.UiPdfPage

fun PdfPage.toUi(): UiPdfPage =
    UiPdfPage(
        index = index,
        bitmap = bitmap,
        width = width,
        height = height
    )
