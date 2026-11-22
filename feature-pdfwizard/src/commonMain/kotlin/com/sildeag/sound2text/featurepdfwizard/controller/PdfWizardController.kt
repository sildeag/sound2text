package com.sildeag.sound2text.featurepdfwizard.controller

import com.sildeag.sound2text.featurepdfwizard.viewmodels.PdfWizardViewModel
import com.sildeag.sound2text.uicommon.state.PdfWizardWorkflowStep

class PdfWizardController(
    private val vm: PdfWizardViewModel
) {

    fun onFileSelected(path: String) {
        vm.loadPdf(path)
    }

    fun onFieldsDiscovered() {
        vm.advance(PdfWizardWorkflowStep.FillFields)
    }

    fun onFieldsCompleted() {
        vm.advance(PdfWizardWorkflowStep.Review)
    }

    fun onSave() {
        vm.advance(PdfWizardWorkflowStep.Save)
    }

    fun onError(message: String) {
        vm.setError(message)
    }
}
