package com.sildeag.sound2text.pdfandroid


import com.sildeag.sound2text.core.pdf.api.PdfMetadataExtractor
import com.sildeag.sound2text.core.pdf.model.PdfMetadata

class AndroidPdfMetadataExtractor : PdfMetadataExtractor {
    override fun extract(bytes: ByteArray): PdfMetadata {
        return PdfMetadata(
            title = null,
            author = null,
            creationDate = null,
            modificationDate = null
        )
    }
}
