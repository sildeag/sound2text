package com.sildeag.sound2text.core.pdf.api

import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.core.pdf.model.PdfPage

interface PdfLoader {
    suspend fun load(bytes: ByteArray): LoadedPdf
}
data class LoadedPdf(
    val pages: List<PdfPage>,
    val metadata: PdfMetadata
)