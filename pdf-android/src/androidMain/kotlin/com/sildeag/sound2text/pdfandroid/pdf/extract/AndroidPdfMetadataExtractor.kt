package com.sildeag.sound2text.pdfandroid.pdf.extract

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfDocumentInfo
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.api.PdfMetadataExtractor
import com.sildeag.sound2text.core.pdf.model.PdfMetadata

class AndroidPdfMetadataExtractor : PdfMetadataExtractor {

    override fun extract(bytes: ByteArray): PdfMetadata {
        val reader = PdfReader(bytes.inputStream())
        val pdf = PdfDocument(reader)
        return try {
            extract(pdf)
        } finally {
            pdf.close()
            reader.close()
        }
    }

    fun extract(pdf: PdfDocument): PdfMetadata {
        val info: PdfDocumentInfo = pdf.documentInfo
        return PdfMetadata(
            title = null,
            author = null,
            creationDate = null,
            modificationDate = null,
        )
    }
}
/*
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
*/