package com.sildeag.sound2text.core.repository

import com.sildeag.sound2text.core.pdf.PdfDocument

interface PdfRepository {
    suspend fun loadPdf(path: String): PdfDocument
}