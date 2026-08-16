package com.sildeag.sound2text.core.pdf.io

import com.sildeag.sound2text.core.pdf.model.PdfForm

interface PdfFormScanner {
    suspend fun scan(basePath: String): List<PdfForm>
}