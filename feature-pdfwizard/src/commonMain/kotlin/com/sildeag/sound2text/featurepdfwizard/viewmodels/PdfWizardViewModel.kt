package com.sildeag.sound2text.uicommon.pdfwizard.viewmodel

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.uicommon.state.PdfWizardWorkflowState
import com.sildeag.sound2text.uicommon.state.PdfWizardWorkflowStep
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

    private val _state = MutableStateFlow(PdfWizardWorkflowState())
    val state: StateFlow<PdfWizardWorkflowState> = _state

    fun loadPdf(path: String) {
        _state.value = _state.value.copy(currentStep = PdfWizardWorkflowStep.LoadDocument)

        scope.launch {
            try {
                val doc: PdfDocument = pdfRepository.loadPdf(path)
                _state.value = _state.value.copy(
                    document = doc,
                    currentStep = PdfWizardWorkflowStep.DiscoverFields
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = "Failed to load PDF: ${e.message}",
                    currentStep = PdfWizardWorkflowStep.SelectFile
                )
            }
        }
    }

    fun advance(step: PdfWizardWorkflowStep) {
        _state.value = _state.value.copy(currentStep = step)
    }

    fun setError(message: String) {
        _state.value = _state.value.copy(error = message)
    }
}
