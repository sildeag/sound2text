package com.sildeag.sound2text.core.pdf.resources

import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.core.usecase.LoadPdfUseCase

class PdfRepositoryImpl(
    private val loadPdfUseCase: LoadPdfUseCase
) : PdfRepository {
    override suspend fun loadPdf(path: String): PdfDocument {
        return loadPdfUseCase(path)
    }
}