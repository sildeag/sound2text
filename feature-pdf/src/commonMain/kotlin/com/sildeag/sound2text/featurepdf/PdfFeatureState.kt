package com.sildeag.sound2text.featurepdf

import com.sildeag.sound2text.core.model.PdfPage
import com.sildeag.sound2text.core.pdf.PdfDocument
import com.sildeag.sound2text.uicommon.model.UiPdfPage

data class PdfFeatureState(
    val document: PdfDocument? = null,
    val pages: List<UiPdfPage> = emptyList(),
    val corePages: List<PdfPage> = emptyList(),
    val extractedText: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)