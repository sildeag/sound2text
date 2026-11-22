package com.sildeag.sound2text.featurepdf.viewmodel

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.featurepdf.PdfFeatureActions
import com.sildeag.sound2text.featurepdf.PdfFeatureState
import com.sildeag.sound2text.uicommon.pdf.processor.UiPdfProcessor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class PdfFeatureViewModel(
    private val uiPdfProcessor: UiPdfProcessor,
    private val renderer: PdfRenderer,
    private val extractor: PdfTextExtractor,
    private val dispatchers: DispatcherProvider,
    private val logger: Logger
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatchers.default)
    private val _state = MutableStateFlow(PdfFeatureState())
    val state: StateFlow<PdfFeatureState> = _state.asStateFlow()
    fun dispatch(action: PdfFeatureActions) {
        when (action) {
            is PdfFeatureActions.LoadUiPdf -> loadUiPdf(action.bytes)
            is PdfFeatureActions.RenderPage -> renderPage(action.pageIndex)
            is PdfFeatureActions.ExtractText -> extractText(action.bytes)
        }
    }
    private fun loadUiPdf(bytes: ByteArray) {
        scope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val uiPages = withContext(dispatchers.io) {
                    uiPdfProcessor.loadUiPdf(bytes)
                }
                _state.update { it.copy(pages = uiPages, isLoading = false) }
            } catch (t: Throwable) {
                logger.e("PdfFeature", "Failed to load PDF", t)
                _state.update {
                    it.copy(error = t.message ?: "Unknown error", isLoading =
                        false)
                }
            }
        }
    }
    private fun renderPage(index: Int) {
        scope.launch {
            try {
                val rendered = withContext(dispatchers.io) {
                    renderer.render(index)
                }
                logger.d("PdfFeature",
                    "Rendered page $index dimensions: ${rendered.width}x${rendered.height}")
            } catch (t: Throwable) {
                logger.e("PdfFeature", "Failed to render page $index", t)
            }
        }
    }
    private fun extractText(bytes: ByteArray) {
        scope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val textList = withContext(dispatchers.io) {
                    extractor.extractText(bytes)
                }
                _state.update {
                    it.copy(extractedText = textList, isLoading = false)
                }
            } catch (t: Throwable) {
                logger.e("PdfFeature", "Failed to extract text", t)
                _state.update {
                    it.copy(error = t.message ?: "Failed to extract text",
                        isLoading = false)
                }
            }
        }
    }
}