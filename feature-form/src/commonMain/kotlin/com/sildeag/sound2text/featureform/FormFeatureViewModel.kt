package com.sildeag.sound2text.featureform

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.pdf.PdfProcessorSelector
import com.sildeag.sound2text.core.pdf.render.PdfRenderer
import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.sildeag.sound2text.core.resource.ResourceLoader
import com.sildeag.sound2text.core.stt.SttEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.sildeag.sound2text.uicommon.mappers.toUi

class FormFeatureViewModel(
    private val processorSelector: PdfProcessorSelector,
    private val renderer: PdfRenderer,
    private val extractor: PdfTextExtractor,
    private val sttEngine: SttEngine,
    private val dispatchers: DispatcherProvider,
    private val logger: Logger,
    private val resources: ResourceLoader
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatchers.default)
    private val _state = MutableStateFlow(FormFeatureState())
    val state: StateFlow<FormFeatureState> = _state
    fun dispatch(action: FormFeatureActions) {
        when (action) {
            is FormFeatureActions.LoadPdf -> loadPdf(action.bytes)
            is FormFeatureActions.RenderPage -> renderPage(action.pageIndex)
            is FormFeatureActions.ExtractFields -> extractFields(action.bytes)
            is FormFeatureActions.TranscribeAudio -> transcribe(action.bytes,
                action.fieldId)
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
                logger.e("FormFeature", "Failed to load PDF", t)
                _state.value = _state.value.copy(error = t.message, isLoading =
                    false)
            }
        }
    }
    private fun renderPage(index: Int) {
        scope.launch {
            val page = _state.value.pages.getOrNull(index) ?: return@launch
            try {
                val rendered = withContext(dispatchers.io)
                { renderer.renderPage(page) }
                _state.value = _state.value.copy(renderedPage = rendered)
            } catch (t: Throwable) {
                logger.e("FormFeature", "Failed to render page", t)
            }
        }
    }
    private fun extractFields(bytes: ByteArray) {
        scope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val textBlocks = withContext(dispatchers.io)
                { extractor.extractText(bytes) }
                val fields = textBlocks.mapIndexed { index, text ->
                    FormField(
                        id = "field_$index",
                        pageIndex = index,
                        label = text.take(40)
                    )
                }
                _state.value = _state.value.copy(fields = fields, isLoading =
                    false)
            } catch (t: Throwable) {
                logger.e("FormFeature", "Failed to extract fields", t)
                _state.value = _state.value.copy(error = t.message, isLoading =
                    false)
            }
        }
    }
    private fun transcribe(bytes: ByteArray, fieldId: String) {
        scope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val text = withContext(dispatchers.io)
                { sttEngine.transcribe(bytes) }
                val updatedFields = _state.value.fields.map {
                    if (it.id == fieldId) it.copy(value = text.transcript) else it
                }
                _state.value = _state.value.copy(
                    fields = updatedFields,
                    transcript = text.transcript,
                    isLoading = false
                )
                logger.d("FormFeature", "Filled field $fieldId with STT text")
            } catch (t: Throwable) {
                logger.e("FormFeature", "STT failed", t)
                _state.value = _state.value.copy(error = t.message, isLoading =
                    false)
            }
        }
    }
}