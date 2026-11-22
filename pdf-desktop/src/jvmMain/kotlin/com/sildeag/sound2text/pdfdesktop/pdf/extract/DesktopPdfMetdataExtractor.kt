package com.sildeag.sound2text.pdfdesktop.pdf.extract

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfDocumentInfo
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.api.PdfMetadataExtractor
import com.sildeag.sound2text.core.pdf.model.PdfMetadata

class DesktopPdfMetadataExtractor : PdfMetadataExtractor {

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
            title = info.title,
            author = info.author,
            creationDate = info.getMoreInfo("CreationDate"),
            modificationDate = info.getMoreInfo("ModDate"),
        )
    }
}