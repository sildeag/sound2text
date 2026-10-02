package com.sildeag.sound2text.featurepdf.storage

import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.core.pdf.model.PdfPage

interface PdfLoader {
    suspend fun load(path: String): PdfDocument
    suspend fun loadBytes(bytes: ByteArray): PdfDocument // optional
    fun getPage(index: Int): PdfPage
}