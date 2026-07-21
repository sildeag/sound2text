package com.sildeag.sound2text.featurepdf

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.pdf.PdfProcessorSelector
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.sildeag.sound2text.core.resource.ResourceLoader
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.uicommon.mappers.toUi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PdfFeatureViewModel(
    private val processorSelector: PdfProcessorSelector,
    private val renderer: PdfRenderer,
    private val extractor: PdfTextExtractor,
    private val resources: ResourceLoader,
    private val dispatchers: DispatcherProvider,
    private val logger: Logger
) {

    private val scope = CoroutineScope(SupervisorJob() + dispatchers.default)

    private val _state = MutableStateFlow(PdfFeatureState())
    val state: StateFlow<PdfFeatureState> = _state

    fun dispatch(action: PdfFeatureActions) {
        when (action) {
            is PdfFeatureActions.LoadPdf -> loadPdf(action.bytes)
            is PdfFeatureActions.RenderPage -> renderPage(action.pageIndex)
            is PdfFeatureActions.ExtractText -> extractText(action.bytes)
        }
    }

    private fun loadPdf(bytes: ByteArray) {
        scope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val processor = processorSelector.select("unified")

            try {
                val doc = withContext(dispatchers.io) { processor.loadPdf(bytes) }

                _state.value = _state.value.copy(
                    document = doc,
                    pages = doc.pages.map { it.toUi() },
                    isLoading = false
                )

            } catch (t: Throwable) {
                logger.e("PdfFeature", "Failed to load PDF", t)
                _state.value = _state.value.copy(
                    error = t.message,
                    isLoading = false
                )
            }
        }
    }

    private fun renderPage(index: Int) {
        scope.launch {
            //val page = _state.value.pages.getOrNull(index) ?: return@launch
            val corePage = _state.value.corePages[index]
            try {
                val rendered = withContext(dispatchers.io) {
                    renderer.renderPage(corePage)
                }
                logger.d("PdfFeature", "Rendered page $index (${rendered.size} bytes)")
            } catch (t: Throwable) {
                logger.e("PdfFeature", "Failed to render page", t)
            }
        }
    }

    private fun extractText(bytes: ByteArray) {
        scope.launch {
            _state.value = _state.value.copy(isLoading = true)

            try {
                val text = withContext(dispatchers.io) {
                    extractor.extractText(bytes)
                }
                _state.value = _state.value.copy(
                    extractedText = text,
                    isLoading = false
                )
            } catch (t: Throwable) {
                logger.e("PdfFeature", "Failed to extract text", t)
                _state.value = _state.value.copy(
                    error = t.message,
                    isLoading = false
                )
            }
        }
    }
}
