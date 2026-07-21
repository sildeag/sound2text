package com.sildeag.sound2text.core.pdf.io

interface PdfFormScanner {
    suspend fun scan(basePath: String): List<PdfForm>
}