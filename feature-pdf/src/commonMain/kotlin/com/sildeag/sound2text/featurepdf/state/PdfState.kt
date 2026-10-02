package com.sildeag.sound2text.featurepdf.state

import com.sildeag.sound2text.uicommon.models.UiPdfPage

data class PdfState(
    val isLoading: Boolean = false,
    val document: UiPdfDocument? = null,
    val currentPage: UiPdfPage? = null,
    val extractedText: String? = null
)