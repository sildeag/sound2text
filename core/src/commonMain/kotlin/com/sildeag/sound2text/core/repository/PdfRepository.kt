package com.sildeag.sound2text.core.repository

import com.sildeag.sound2text.core.pdf.model.PdfDocument

interface PdfRepository {
    suspend fun loadPdf(path: String): PdfDocument
}