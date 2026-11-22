package com.sildeag.sound2text.core.repository

import com.sildeag.sound2text.core.usecase.LoadPdfUseCase

class PdfRepositoryImpl(
    private val loadPdfUseCase: LoadPdfUseCase
) : PdfRepository {
    override suspend fun loadPdf(path: String): ByteArray {
        return loadPdfUseCase(path)
    }
}