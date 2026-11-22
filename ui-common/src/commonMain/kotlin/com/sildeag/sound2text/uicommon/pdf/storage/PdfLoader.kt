package com.sildeag.sound2text.uicommon.pdf.storage

import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfDocument
import com.sildeag.sound2text.uicommon.pdf.models.UiPdfPage

//import com.sildeag.sound2text.featurepdf.models.UiPdfDocument
//import com.sildeag.sound2text.featurepdf.models.UiPdfPage

interface PdfLoader {
    suspend fun load(path: String): UiPdfDocument
    suspend fun loadBytes(bytes: ByteArray, fileName: String = ""): UiPdfDocument

    fun getPage(index: Int): UiPdfPage        // UI Model for UI consumption
    fun getCorePage(index: Int): PdfPage      // Core Domain Model for Mapper/Processor
}
