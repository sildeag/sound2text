package com.sildeag.sound2text.core.usecase

import core.model.PdfDocument
class LoadPdfUseCase(
    private val loader: PdfLoader
) {
    suspend operator fun invoke(path: String): PdfDocument {
        return loader.load(path)
    }
}
