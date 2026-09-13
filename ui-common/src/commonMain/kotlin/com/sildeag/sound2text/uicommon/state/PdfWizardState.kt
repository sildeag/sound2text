package com.sildeag.sound2text.uicommon.state

import com.sildeag.sound2text.core.pdf.model.PdfFieldDescriptor

data class PdfWizardState(
    val currentStep: PdfWizardStep = PdfWizardStep.SelectPdf,

    // PDF selection
    val selectedPdf: Any? = null,
    val pdfBytes: ByteArray? = null,

    // Field discovery
    val fields: List<PdfFieldDescriptor> = emptyList(),
    val currentFieldIndex: Int = 0,

    // Field filling
    val filledValues: Map<String, String> = emptyMap(),
    val fieldErrors: Map<String, String> = emptyMap(),

    // PDF generation
    val outputPdfBytes: ByteArray? = null,
    val savePath: String? = null,

    // UI helpers
    val isBusy: Boolean = false,
    val showFieldList: Boolean = false,
    val showPreview: Boolean = false,

    // Errors
    val wizardError: String? = null,

    // STT integration
    val recordingState: RecordingState = RecordingState.Idle
) {
    val currentField: PdfFieldDescriptor?
        get() = fields.getOrNull(currentFieldIndex)

    val isRecording: Boolean
        get() = recordingState == RecordingState.Recording
}

enum class PdfWizardStep {
    SelectPdf,
    DiscoverFields,
    FillFields,
    Review,
    Save
}

enum class RecordingState {
    Idle,
    Recording,
    Processing
}
