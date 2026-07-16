package com.sildeag.sound2text.core.pdf.data

import core.repository.PdfRepository
import core.model.PdfDocument
import core.usecase.LoadPdfUseCase
class PdfRepositoryImpl(
    private val loadPdfUseCase: LoadPdfUseCase
) : PdfRepository {
    override suspend fun loadPdf(path: String): PdfDocument {
        return loadPdfUseCase(path)
    }
}