package com.sildeag.sound2text.featurepdf.storage

import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.featurepdf.models.UiPdfDocument
import com.sildeag.sound2text.featurepdf.models.UiPdfPage

interface PdfLoader {
    suspend fun load(path: String): UiPdfDocument
    suspend fun loadBytes(bytes: ByteArray): UiPdfDocument // optional
    fun getPage(index: Int): UiPdfPage
}