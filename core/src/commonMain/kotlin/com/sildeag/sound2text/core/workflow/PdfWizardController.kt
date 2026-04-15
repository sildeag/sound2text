package com.sildeag.sound2text.core.workflow

import com.sildeag.sound2text.core.pdf.PdfFormDescriptor
import com.sildeag.sound2text.core.pdf.UnifiedFormRegistry
import com.sildeag.sound2text.core.stt.UnifiedEngineRegistry
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttResult
class PdfWizardController(
    private val formRegistry: UnifiedFormRegistry,
    private val sttRegistry: UnifiedEngineRegistry,
    private val formsBasePath: String,
    private val modelsBasePath: String
) {
    var state: PdfWizardState = PdfWizardState()
        private set
    fun loadForms() {
        val forms = formRegistry.listForms(formsBasePath)
        state = state.copy(
            availableForms = forms,
            step = PdfWizardStep.SelectForm
        )
    }
    fun selectForm(form: PdfFormDescriptor) {
        state = state.copy(
            selectedForm = form,
            step = PdfWizardStep.MapFields(form)
        )
    }
    fun setMappings(mappings: List<FieldMapping>) {
        state = state.copy(
            mappings = mappings,
            currentFieldIndex = 0,
            step = PdfWizardStep.FillFields
        )
    }
    fun nextField() {
        val nextIndex = state.currentFieldIndex + 1
        if (nextIndex >= state.mappings.size) {
            state = state.copy(step = PdfWizardStep.Completed)
        } else {
            state = state.copy(currentFieldIndex = nextIndex)
        }
    }
    fun fillCurrentFieldWithStt(
        audioBytes: ByteArray,
        outputPath: String
    ) {
        val mapping = state.currentField ?: return
        val form = state.selectedForm ?: return
        val plugin = formRegistry.getPlugin(form.engine) ?: return
        val factory = plugin.createFactory()
        val engine = factory.load(form)
        val sttEnginePlugin =
            sttRegistry.getPlugin(mapping.sttEngine) ?: return
        val sttFactory = sttEnginePlugin.createFactory()
        val sttConfig = SttConfig(
            engine = mapping.sttEngine,
            modelName = mapping.modelName,
            language = mapping.language
        )
        val sttEngine = sttFactory.load(sttConfig)
        val result: SttResult = sttEngine.transcribe(audioBytes)
        engine.fillField(mapping.field.name, result.text)
        engine.saveTo(outputPath)
        state = state.copy(outputPath = outputPath)
        nextField()
    }
}