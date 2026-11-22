package com.sildeag.sound2text.pdfandroid.pdf.extract

import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage

data class AndroidLoadedUiPdf(
    val pages: MutableList<UiPdfPage>,
    val metadata: PdfMetadata
)