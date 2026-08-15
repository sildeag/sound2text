package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.pdfwizard.PdfWizardController
import com.sildeag.sound2text.pdfwizard.PdfWizardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class PdfWizardViewModel(
    private val controller: PdfWizardController
) {
    private val scope = CoroutineScope(Dispatchers.Default)
    private val _state = MutableStateFlow(PdfWizardState())
    val state: StateFlow<PdfWizardState> = _state
    fun selectPdf(ref: Any) {
        scope.launch {
            controller.selectPdf(ref)
            _state.update { controller.state }
        }
    }
    fun discoverFields() {
        scope.launch {
            controller.discoverFields()
            _state.update { controller.state }
        }
    }
    fun selectField(index: Int) {
        controller.selectField(index)
        _state.update { controller.state }
    }
    fun nextField() {
        controller.nextField()
        _state.update { controller.state }
    }
    fun previousField() {
        controller.previousField()
        _state.update { controller.state }
    }
    fun startRecording() {
        scope.launch {
            controller.startRecording()
            _state.update { controller.state }
        }
    }
    fun stopRecording() {
        scope.launch {
            controller.stopRecording()
            _state.update { controller.state }
        }
    }
    fun applySttResult() {
        controller.applySttResult()
        _state.update { controller.state }
    }
    fun manualEntry(text: String) {
        controller.manualEntry(text)
        _state.update { controller.state }
    }
    fun generatePdf() {
        scope.launch {
            controller.generatePdf()
            _state.update { controller.state }
        }
    }
    fun savePdf(path: String) {
        scope.launch {
            controller.savePdf(path)
            _state.update { controller.state }
        }
    }
}