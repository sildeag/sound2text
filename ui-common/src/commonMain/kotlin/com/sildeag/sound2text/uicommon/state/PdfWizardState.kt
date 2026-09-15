package com.sildeag.sound2text.uicommon.pdfwizard.state

import com.sildeag.sound2text.core.pdf.model.PdfDocument

data class PdfWizardState(
    val currentStep: WizardStep = WizardStep.SelectFile,
    val document: PdfDocument? = null,
    val error: String? = null
)

enum class WizardStep {
    SelectFile,
    LoadDocument,
    DiscoverFields,
    FillFields,
    Review,
    Save
}
