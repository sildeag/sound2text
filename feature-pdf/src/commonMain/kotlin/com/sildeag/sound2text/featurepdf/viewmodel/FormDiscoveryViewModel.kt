package com.sildeag.sound2text.featurepdf.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.core.pdf.PdfForm
import com.sildeag.sound2text.core.pdf.SttFormManager
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
