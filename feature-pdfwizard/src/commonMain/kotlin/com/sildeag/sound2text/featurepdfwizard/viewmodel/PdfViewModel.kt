package com.sildeag.sound2text.featurepdfwizard.viewmodel

import com.sildeag.sound2text.core.pdf.PdfDocument
import com.sildeag.sound2text.core.repository.PdfRepository
import core.model.PdfDocument
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import core.dispatcher.DispatcherProvider

class PdfViewModel(
    private val pdfRepository: PdfRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcherProvider.io)
    private val _pdfState = MutableStateFlow<PdfDocument?>(null)
    val pdfState: StateFlow<PdfDocument?> = _pdfState
    fun load(path: String) {
        scope.launch {
            val doc = pdfRepository.loadPdf(path)
            _pdfState.value = doc
        }
    }
}

