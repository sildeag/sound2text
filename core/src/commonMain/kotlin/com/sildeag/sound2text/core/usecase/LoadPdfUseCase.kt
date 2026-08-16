package com.sildeag.sound2text.core.usecase

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.core.pdf.model.PdfDocument

class LoadPdfUseCase(
    private val loader: PdfResourceLoader
) {
    suspend operator fun invoke(path: String): PdfDocument {
        return loader.load(path)
    }
}
