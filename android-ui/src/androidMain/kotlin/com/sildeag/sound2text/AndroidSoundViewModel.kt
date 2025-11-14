package com.sildeag.sound2text.`android-ui`

class SoundViewModel : ViewModel() {
    private val _noteText = MutableStateFlow("")
    val noteText: StateFlow<String> = _noteText
    private val _isListening = MutableStateFlow(false)
    val isListening: StateFlow<Boolean> = _isListening
    private val _statusMessage = MutableStateFlow("Ready")
    val statusMessage: StateFlow<String> = _statusMessage
    fun onNoteChanged(newText: String) {
        _noteText.value = newText
    }
    fun toggleListening() {
        _isListening.value = !_isListening.value
        _statusMessage.value = if (_isListening.value) "Listening..." else
            "Stopped"
    }
    fun saveToPdf() {
        // Inject PdfWriterFactory and call write(...)
        _statusMessage.value = "Saved to PDF"
    }
}
