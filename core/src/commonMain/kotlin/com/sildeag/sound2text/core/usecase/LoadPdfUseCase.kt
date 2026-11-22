package com.sildeag.sound2text.core.usecase

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader

class LoadPdfUseCase(
    private val loader: PdfResourceLoader
) {
    suspend operator fun invoke(path: String): ByteArray {
        return loader.load(path)
    }
}
