package com.sildeag.sound2text.featurepdf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.core.pdf.discovery.SttFormManager
import com.sildeag.sound2text.core.pdf.model.PdfForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormDiscoveryViewModel(
    private val manager: SttFormManager
) : ViewModel() {
    private val _forms = MutableStateFlow<Map<String,
            List<PdfForm>>>(emptyMap())
    val forms = _forms.asStateFlow()
    fun loadForms() {
        viewModelScope.launch {
            _forms.value = manager.discoverForms("forms")
        }
    }
}
