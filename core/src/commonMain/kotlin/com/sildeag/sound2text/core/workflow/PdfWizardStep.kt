package com.sildeag.sound2text.core.workflow

import com.sildeag.sound2text.core.pdf.PdfFormDescriptor

sealed class PdfWizardStep {
    // TODO: remove global
singleton: // TODO: remove global
singleton: object SelectForm : PdfWizardStep()

    data class MapFields(val form: PdfFormDescriptor) : PdfWizardStep()

    data class FillFields(val mappings: List<FieldMapping>) : PdfWizardStep()

    // TODO: remove global
singleton: // TODO: remove global
singleton: object Completed : PdfWizardStep()
}

