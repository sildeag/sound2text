package com.sildeag.sound2text.uicommon.pdf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.uicommon.logic.SettingsStore
import com.sildeag.sound2text.uicommon.state.PdfState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PdfViewModel(
    private val pdfLoader: UiPdfLoader,
    private val settingsStore: SettingsStore
) : ViewModel() {

    private val _state = MutableStateFlow(PdfState())
    val state: StateFlow<PdfState> = _state

    fun loadPdf(path: String) {
        viewModelScope.launch {
            val document = pdfLoader.load(path)
            _state.value = PdfState(
                path = path,
                pages = document.pages
            )
        }
    }
}
