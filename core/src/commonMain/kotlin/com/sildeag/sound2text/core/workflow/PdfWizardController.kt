package com.sildeag.sound2text.core.workflow

import com.sildeag.sound2text.core.pdf.PdfFormDescriptor
import com.sildeag.sound2text.core.pdf.UnifiedFormRegistry
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttResult
import com.sildeag.sound2text.core.stt.UnifiedEngineRegistry
import com.sildeag.sound2text.core.workflow.PdfWizardStep.*

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
            step = SelectForm
        )
    }
    fun selectForm(form: PdfFormDescriptor) {
        state = state.copy(
            selectedForm = form,
            step = MapFields(form)
        )
    }

    fun setMappings(mappings: List<FieldMapping>) {
        state = state.copy(
            mappings = mappings,
            currentFieldIndex = 0,
            step = FillFields(mappings)
        )
    }
    fun nextField() {
        val nextIndex = state.currentFieldIndex + 1
        if (nextIndex >= state.mappings.size) {
            state = state.copy(step = Completed)
        } else {
            state = state.copy(currentFieldIndex = nextIndex)
        }
    }
    suspend fun fillCurrentFieldWithStt(
        audioBytes: ByteArray,
        outputPath: String
    ) {
        val mapping = state.currentField ?: return
        val form = state.selectedForm ?: return
        val plugin = formRegistry.getPlugin(form.engine) ?: return
        val factory = plugin.createFactory()
        val engine = factory.load(form)
        val sttEnginePlugin = sttRegistry.getPlugin(mapping.engineName) ?: return
        val sttFactory = sttEnginePlugin.createFactory()
        val sttConfig = SttConfig(
            engineName = mapping.engineName,
            language = mapping.language,
            modelPath = mapping.modelPath,
            modelFile = mapping.modelFile,
            androidModelDir = mapping.androidModelDir,
            androidModelFile = mapping.androidModelFile,
            sampleRate = 16_000f
        )
        val sttEngine = sttFactory.load(sttConfig)
        val result: SttResult = sttEngine.transcribe(audioBytes)
        engine.fillField(mapping.field.name, result.engineName)
        engine.saveTo(outputPath)
        state = state.copy(outputPath = outputPath)
        nextField()
    }
}
