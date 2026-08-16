package com.sildeag.sound2text.core.pdf.io

import com.sildeag.sound2text.core.pdf.model.PdfDocument

interface PdfResourceLoader {
    suspend fun load(path: String): PdfDocument
}