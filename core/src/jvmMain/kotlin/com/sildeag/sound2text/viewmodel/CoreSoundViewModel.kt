package com.sildeag.sound2text.viewmodel

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.service.note.NoteEditor
import com.sildeag.sound2text.service.note.NoteProvider
class CoreSoundViewModel(
    private val noteEditor: NoteEditor,
    val pulseLogic: PulseLogic,
    private val settings: AppSettings,
    private val noteProvider: NoteProvider
) {
    internal var isListening = false
    fun onTextFromPulse(text: String): String {
        noteEditor.append(text)
        return noteEditor.getText()
    }
    fun onMicStateChanged(isOn: Boolean): Boolean {
        isListening = isOn
        return isListening
    }
    fun startListening() = pulseLogic.startListening()
    fun stopListening() = pulseLogic.stopListening()
    fun correctGrammar(): String {
        val corrected = noteEditor.grammarCheck()
        noteEditor.setText(corrected)
        return corrected
    }
    fun generatePDF(outputPath: String) {
        noteProvider.generatePDF(outputPath)
    }
}
