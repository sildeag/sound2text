package com.sildeag.sound2text.uicommon.state

import com.sildeag.sound2text.uicommon.models.UiPdfPage

data class PdfState(
    val path: String? = null,
    val pages: List<UiPdfPage> = emptyList()
)
