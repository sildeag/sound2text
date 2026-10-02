package com.sildeag.sound2text.featurepdfviewer.viewmodels

import com.sildeag.sound2text.featurepdfviewer.models.UiPdfForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.sildeag.sound2text.featurepdfviewer.loader.PdfFormLoader

class PdfFormListViewModel(
    private val formLoader: PdfFormLoader
) {
    private val _forms =
        MutableStateFlow<List<UiPdfForm>>(emptyList())
    val forms: StateFlow<List<UiPdfForm>> = _forms
    suspend fun loadForms() {
        val coreForms = formLoader.discoverForms()
        _forms.value = coreForms.map {
            UiPdfForm(
                name = it.formName,
                path = it.path,
                fieldCount = it.fields.size
            )
        }
    }
}

