package com.sildeag.sound2text.uicommon.pdf.storage

import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.uicommon.pdf.mappers.DefaultPdfUiMapper
import com.sildeag.sound2text.uicommon.pdf.mappers.PdfUiMapper
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfDocument
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage

//import com.sildeag.sound2text.featurepdf.mappers.DefaultPdfUiMapper
//import com.sildeag.sound2text.featurepdf.mappers.PdfUiMapper
//import com.sildeag.sound2text.featurepdf.models.UiPdfDocument
//import com.sildeag.sound2text.featurepdf.models.UiPdfPage

class UiPdfLoader(
    private val resourceLoader: PdfResourceLoader,
    private val processor: PdfProcessor,
    private val renderer: PdfRenderer,
    private val mapper: PdfUiMapper<UiPdfPage> = DefaultPdfUiMapper()
) {
    suspend fun load(path: String): UiPdfDocument {
        // 1. Load the core PdfDocument wrapper
        val pdfDocument = resourceLoader.load(path)
        val bytes: ByteArray = resourceLoader.loadBytes(path) // <-- Generated here

        // 2. Extract raw bytes from the PdfDocument (assuming pdfDocument.bytes exists)
        return loadBytes(bytes, fileName = path.substringAfterLast('/'))
    }


    suspend fun loadBytes(bytes: ByteArray, fileName: String = ""): UiPdfDocument {
        val corePages = processor.loadPdf(bytes)

        val uiPages = corePages.map { corePage ->
            val rendered = renderer.render(corePage.index)
            mapper.map(
                core = corePage,
                bitmap = rendered.bitmap,
                width = rendered.width,
                height = rendered.height
            )
        }

        return UiPdfDocument(
            name = fileName,
            pageCount = uiPages.size,
            pages = uiPages,
            metadata = null
        )
    }
}
/*
import com.sildeag.sound2text.core.pdf.io.PdfResourceLoader
import com.sildeag.sound2text.core.pdf.processor.PdfProcessor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.featurepdf.mappers.DefaultPdfUiMapper
import com.sildeag.sound2text.featurepdf.mappers.PdfUiMapper
import com.sildeag.sound2text.featurepdf.models.UiPdfPage
import com.sildeag.sound2text.featurepdf.models.UiPdfDocument

class UiPdfLoader(
    private val resourceLoader: PdfResourceLoader,
    private val processor: PdfProcessor,
    private val renderer: PdfRenderer,
    private val mapper: PdfUiMapper<UiPdfPage> = DefaultPdfUiMapper()
) {
    suspend fun load(path: String): UiPdfDocument {

        val bytes = resourceLoader.load(path)

        val pdfDocument = processor.loadPdf(bytes)

        val uiPages = pdfDocument.pages.map { corePage ->

            val rendered = renderer.render(corePage.index)

            mapper.map(
                corePage,
                rendered.bitmap,
                rendered.width,
                rendered.height
            )
        }

        return UiPdfDocument(
            name = pdfDocument.name,
            pageCount = pdfDocument.pages.size,
            pages = uiPages,
            metadata = pdfDocument.metadata
        )
    }
}
*/
