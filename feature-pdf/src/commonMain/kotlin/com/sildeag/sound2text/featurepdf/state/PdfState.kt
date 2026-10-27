package com.sildeag.sound2text.featurepdf.state

import com.sildeag.sound2text.featurepdf.models.UiPdfDocument
import com.sildeag.sound2text.featurepdf.models.UiPdfPage

data class PdfState(
    val isLoading: Boolean = false,
    val document: UiPdfDocument? = null,
    val currentPage: UiPdfPage? = null,
    val extractedText: String? = null
)