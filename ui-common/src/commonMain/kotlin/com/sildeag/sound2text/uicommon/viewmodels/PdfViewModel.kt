package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.uicommon.state.PdfState
import com.sildeag.sound2text.uicommon.models.UiPdfDocument
import com.sildeag.sound2text.uicommon.mappers.DefaultPdfUiMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class PdfViewModel(
    private val loader: PdfLoader, // platform loader
    private val renderer: PdfRenderer, // platform renderer
    private val mapper: DefaultPdfUiMapper // shared mapper
) {
    private val _state = MutableStateFlow(PdfState())
    val state: StateFlow<PdfState> = _state
    suspend fun load(path: String) {
        _state.update { it.copy(isLoading = true) }
        val coreDoc = loader.load(path)
        val uiDoc = UiPdfDocument(
            name = coreDoc.name,
            pageCount = coreDoc.pageCount,
            pages = coreDoc.pages.map { UiPdfPageSummary(it.index) },
            metadata = coreDoc.metadata
        )
        _state.update { it.copy(document = uiDoc, isLoading =
            false) }
    }
    suspend fun renderPage(index: Int) {
        val bitmap = renderer.render(index)
        val corePage = loader.getPage(index)
        val uiPage = mapper.map(corePage, bitmap,
            bitmapWidth(bitmap), bitmapHeight(bitmap))
        _state.update { it.copy(currentPage = uiPage) }
    }
}
