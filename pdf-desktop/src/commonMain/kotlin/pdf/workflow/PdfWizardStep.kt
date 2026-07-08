package pdf.workflow

import pdf.PdfFormDescriptor

sealed class PdfWizardStep {
object SelectForm : PdfWizardStep()

    data class MapFields(val form: PdfFormDescriptor) : PdfWizardStep()

    data class FillFields(val mappings: List<FieldMapping>) : PdfWizardStep()

object Completed : PdfWizardStep()
}

