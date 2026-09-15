package com.sildeag.sound2text.uicommon.pdfwizard.viewmodel

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.uicommon.pdfwizard.state.PdfWizardState
import com.sildeag.sound2text.uicommon.pdfwizard.state.WizardStep
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PdfWizardViewModel(
    private val pdfRepository: PdfRepository,
    dispatcherProvider: DispatcherProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatcherProvider.io)

    private val _state = MutableStateFlow(PdfWizardState())
    val state: StateFlow<PdfWizardState> = _state

    fun loadPdf(path: String) {
        _state.value = _state.value.copy(currentStep = WizardStep.LoadDocument)

        scope.launch {
            try {
                val doc: PdfDocument = pdfRepository.loadPdf(path)
                _state.value = _state.value.copy(
                    document = doc,
                    currentStep = WizardStep.DiscoverFields
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to load PDF: ${e.message}",
                    currentStep = WizardStep.SelectFile
                )
            }
        }
    }

    fun advance(step: WizardStep) {
        _state.value = _state.value.copy(currentStep = step)
    }

    fun setError(message: String) {
        _state.value = _state.value.copy(error = message)
    }
}
