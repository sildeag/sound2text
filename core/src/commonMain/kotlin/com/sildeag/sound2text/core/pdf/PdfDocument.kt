package com.sildeag.sound2text.core.pdf

import com.sildeag.sound2text.core.model.PdfPage
import kotlinx.serialization.Serializable

/**
 * A platform-agnostic representation of a PDF document.
 *
 * This is the shared model used by:
 * - PdfProcessor (load/save)
 * - PdfRenderer (renderPage)
 * - PdfTextExtractor (extractText)
 * - PdfFeature (UI + ViewModel)
 *
 * It does NOT depend on iText or PdfBox.
 */
@Serializable
data class PdfDocument(
    val pages: List<PdfPage>
) {
    val pageCount: Int
        get() = pages.size
    fun page(index: Int): PdfPage =
        pages[index]
}

