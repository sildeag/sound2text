package com.sildeag.sound2text.uicommon.pdf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PdfViewModel(
    private val pdfProcessor: PdfProcessor,
    private val settingsStore: SettingsStore
) : ViewModel() {
    private val _state = MutableStateFlow(PdfState())
    val state: StateFlow<PdfState> = _state
    fun loadPdf(path: String) {
        viewModelScope.launch {
            val pages = pdfProcessor.load(path)
            _state.value = PdfState(
                path = path,
                pages = pages
            )
        }
    }
}