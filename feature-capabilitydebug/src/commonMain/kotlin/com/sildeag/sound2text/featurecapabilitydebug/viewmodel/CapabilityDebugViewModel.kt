package com.sildeag.sound2text.featurecapabilitydebug.viewmodel

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.core.repository.TranscriptionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CapabilityDebugViewModel(
    private val transcriptionRepository: TranscriptionRepository,
    private val pdfRepository: PdfRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcherProvider.io)
    private val _debugInfo = MutableStateFlow("Idle")
    val debugInfo: StateFlow<String> = _debugInfo
    fun testTranscription(bytes: ByteArray) {
        scope.launch {
            val text = transcriptionRepository.transcribe(bytes)
            _debugInfo.value = "Transcription: $text"
        }
    }
    fun testPdf(path: String) {
        scope.launch {
            val doc = pdfRepository.loadPdf(path)
            val uiDoc = uiPdfLoader.load(path)
            _debugInfo.value = "PDF Loaded: ${doc.pages.size} pages"
        }
    }
}