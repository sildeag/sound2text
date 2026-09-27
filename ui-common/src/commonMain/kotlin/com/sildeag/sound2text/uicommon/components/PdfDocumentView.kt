package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.uicommon.models.UiPdfPage
@Composable
fun PdfDocumentView(
    document: PdfDocument,
    pages: List<UiPdfPage> // rendered pages from PdfViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(pages) { page ->
            PdfPageView(page)
        }
    }
}