package com.sildeag.sound2text.`desktop-ui`.ui

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteEditor

class SoundViewModel(
    private val noteEditor: NoteEditor,
    val pulseLogic: PulseLogic,
    private val settings: AppSettings,
    private val noteProvider: NoteProvider,
    private val onText: (String) -> Unit,
    private val onMic: (Boolean) -> Unit,
    private val onPulseColor: (String) -> Unit,
    private val onPulseUpdate: (String) -> Unit,
    private val onPulse: (String) -> Unit,
    private val environment: (String)
) {

    internal var isListening = false

    init {
        pulseLogic.pulseConfig(
            onText = { text ->
                noteEditor.append(text)
                onText(noteEditor.getText())
            },
            onMic = { isOn ->
                isListening = isOn
                onMic(isOn)
            },
            onPulse = { color ->
                onPulseColor(color)
            }
        )
    }


    fun toggleMic() {
        isListening = !isListening
        onMic?.invoke(isListening)
        if (isListening) pulseLogic.startListening() else pulseLogic.stopListening()
    }

    fun correctGrammar(): String {
        val corrected = noteEditor.grammarCheck()
        noteEditor.setText(corrected)
        return corrected
    }

    fun onFocusLost() {
        if (isListening) {
            pulseLogic.stopListening()
            onMic?.invoke(false)
        }
    }

    fun generatePDF(outputPath: String){
        noteProvider.generatePDF(outputPath)
    }

    fun showToast(message: String) { println(message) }

}