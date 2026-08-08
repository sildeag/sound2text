package com.sildeag.sound2text.featureform

import com.sildeag.sound2text.core.pdf.PdfDocument
import com.sildeag.sound2text.uicommon.models.UiPdfPage

data class FormFeatureState(
    val document: PdfDocument? = null,
    val pages: List<UiPdfPage> = emptyList(),
    val corePages: List<PdfPage> = emptyList(),
    val fields: List<FormField> = emptyList(),
    val renderedPage: ByteArray? = null,
    val transcript: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
