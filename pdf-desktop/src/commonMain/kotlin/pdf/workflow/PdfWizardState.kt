package pdf.workflow

import com.sildeag.sound2text.core.pdf.PdfFormDescriptor
data class PdfWizardState(
    val step: PdfWizardStep = PdfWizardStep.SelectForm,
    val availableForms: List<PdfFormDescriptor> = emptyList(),
    val selectedForm: PdfFormDescriptor? = null,
    val mappings: List<FieldMapping> = emptyList(),
    val currentFieldIndex: Int = 0,
    val outputPath: String? = null
) {
    val currentField: FieldMapping?
        get() = mappings.getOrNull(currentFieldIndex)
}
