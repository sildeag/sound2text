package com.sildeag.sound2text.uicommon.state
import com.sildeag.sound2text.core.pdf.model.PdfDocument
import com.sildeag.sound2text.uicommon.models.UiPdfPage
import com.sildeag.sound2text.featurepdfwizard.model.UiPdfField
data class UiPdfDocument(
    val name: String,
    val pageCount: Int
)
enum class PdfWizardWorkflowStep {
    SelectFile,
    LoadDocument,
    DiscoverFields,
    FillFields,
    Review,
    Save,
    Done
}
enum class SaveMode {
    Save,
    SaveAs
}
data class PdfWizardWorkflowState(
    val document: PdfDocument? = null,
    val fields: List<UiPdfField> = emptyList(),
    val currentStep: PdfWizardWorkflowStep = PdfWizardWorkflowStep.SelectFile,
    val pages: List<UiPdfPage> = emptyList(),
    val defaultOutputPath: String? = null,
    val chosenOutputPath: String? = null,
    val saveMode: SaveMode = SaveMode.Save,
    val error: String? = null
)


