package com.sildeag.sound2text.uicommon.pdf

import com.itextpdf.kernel.pdf.PdfPage

data class PdfState(
    val path: String? = null,
    val pages: List<PdfPage> = emptyList()
)