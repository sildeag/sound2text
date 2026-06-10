package com.sildeag.sound2text.uicommon.viewmodel

class SettingsViewModel(
    private val settingsStore: SettingsStore,
    private val availableEngines: List<String>,
    private val availablePdfProcessors: List<String>
) : ViewModel() {
    private val _state = MutableStateFlow(SettingsState())
    val state: StateFlow<SettingsState> = _state
    init {
        val settings = settingsStore.load()
        _state.value = SettingsState(
            selectedEngine = settings.selectedEngine,
            selectedPdfProcessor = settings.selectedPdfProcessor,
            language = settings.language,
            autoSaveNotes = settings.autoSaveNotes,
            enableVoiceInput = settings.enableVoiceInput,
            theme = settings.theme,
            availableEngines = availableEngines,
            availablePdfProcessors = availablePdfProcessors
        )
    }
    fun updateEngine(engine: String) {
        _state.update { it.copy(selectedEngine = engine) }
        save()
    }
    fun updatePdfProcessor(pdf: String) {
        _state.update { it.copy(selectedPdfProcessor = pdf) }
        save()
    }
    fun updateLanguage(lang: String) {
        _state.update { it.copy(language = lang) }
        save()
    }
    private fun save() {
        val s = _state.value
        settingsStore.save(
            AppSettings(
                selectedEngine = s.selectedEngine,
                selectedPdfProcessor = s.selectedPdfProcessor,
                language = s.language,
                autoSaveNotes = s.autoSaveNotes,
                enableVoiceInput = s.enableVoiceInput,
                theme = s.theme
            )
        )
    }
}