package com.sildeag.sound2text.core.pdf

interface PdfFormScanner {
    suspend fun scan(basePath: String): List<PdfForm>
}
