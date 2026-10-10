package com.sildeag.sound2text.pdfdesktop


import kotlinx.datetime.Instant
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfDocumentInfo
import com.sildeag.sound2text.core.pdf.api.PdfMetadataExtractor
import com.sildeag.sound2text.core.pdf.model.PdfMetadata

class DesktopPdfMetadataExtractor : PdfMetadataExtractor {
    fun extract(pdf: PdfDocument): PdfMetadata {
        val info: PdfDocumentInfo = pdf.documentInfo
        return PdfMetadata(
            title = info.title,
            author = info.author,
            creationDate = info.getMoreInfo("CreationDate"),
            modificationDate = info.getMoreInfo("ModDate")
        )
    }
    override fun extract(bytes: ByteArray): PdfMetadata {
        val reader =
            com.itextpdf.kernel.pdf.PdfReader(bytes.inputStream())
        val pdf = PdfDocument(reader)
        return extract(pdf)
    }
}