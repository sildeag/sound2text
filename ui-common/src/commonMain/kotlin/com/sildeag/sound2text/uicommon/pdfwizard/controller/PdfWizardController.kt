package com.sildeag.sound2text.uicommon.pdfwizard.controller

import com.sildeag.sound2text.uicommon.pdfwizard.state.WizardStep
import com.sildeag.sound2text.uicommon.pdfwizard.viewmodel.PdfWizardViewModel

class PdfWizardController(
    private val vm: PdfWizardViewModel
) {

    fun onFileSelected(path: String) {
        vm.loadPdf(path)
    }

    fun onFieldsDiscovered() {
        vm.advance(WizardStep.FillFields)
    }

    fun onFieldsCompleted() {
        vm.advance(WizardStep.Review)
    }

    fun onSave() {
        vm.advance(WizardStep.Save)
    }

    fun onError(message: String) {
        vm.setError(message)
    }
}
