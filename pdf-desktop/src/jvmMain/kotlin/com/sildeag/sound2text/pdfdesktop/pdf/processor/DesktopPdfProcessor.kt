package com.sildeag.sound2text.pdfdesktop.pdf.processor

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.sildeag.sound2text.core.pdf.model.PdfMetadata
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.pdfdesktop.pdf.extract.DesktopLoadedUiPdf
import com.sildeag.sound2text.pdfdesktop.pdf.extract.DesktopPdfMetadataExtractor
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.ByteArrayInputStream

class DesktopPdfProcessor(
    private val metadataExtractor: DesktopPdfMetadataExtractor = DesktopPdfMetadataExtractor()
) : PdfProcessor {

    // 1. Core text loading via PDFBox (fulfills PdfProcessor)
    override suspend fun loadPdf(bytes: ByteArray): List<PdfPage> {
        return ByteArrayInputStream(bytes).use { inputStream ->
            PDDocument.load(inputStream).use { document ->
                val stripper = PDFTextStripper()
                (0 until document.numberOfPages).map { index ->
                    stripper.startPage = index + 1
                    stripper.endPage = index + 1
                    val pageText = stripper.getText(document).trim()

                    PdfPage(
                        index = index,
                        pageNumber = index + 1,
                        text = pageText
                    )
                }
            }
        }
    }

    // 2. Metadata extraction via iText
    fun extractMetadata(bytes: ByteArray): PdfMetadata {
        return PdfReader(ByteArrayInputStream(bytes)).use { reader ->
            PdfDocument(reader).use { pdf ->
                metadataExtractor.extract(pdf)
            }
        }
    }

    // 3. Combined UI loading + metadata extraction
    suspend fun loadUiPdf(bytes: ByteArray, uiPdfProcessor: UiPdfProcessor): DesktopLoadedUiPdf {
        val uiPages: MutableList<UiPdfPage> = uiPdfProcessor.loadUiPdf(bytes)
        val metadata = extractMetadata(bytes)
        return DesktopLoadedUiPdf(
            pages = uiPages,
            metadata = metadata
        )
    }
}
