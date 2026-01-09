package com.sildeag.sound2text.service.logic

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.logging.Logger
import com.sildeag.sound2text.service.note.NoteEditor
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.service.recognizer.Recognizer
import com.sildeag.sound2text.stt.SoundPlayer
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.util.Duration
import kotlin.invoke

class PulseLogic(
    private val soundPlayer: SoundPlayer,
    private val recognizer: Recognizer,
    private val noteEditor: NoteEditor,
    private val settings: AppSettings,
    private val logger: Logger,
    private val noteProvider: NoteProvider
) {
    data class PulseCallbacks(
        val onText: (String) -> Unit = {},
        val onMic: (Boolean) -> Unit = {},
        val onPulseColor: (String) -> Unit = {}
    )
    private var callbacks = PulseCallbacks()
    private var pulseTimeline: Timeline? = null
    private var pulseState = true
    private var isListening = false
    fun configureCallbacks(cb: PulseCallbacks) {
        callbacks = cb
    }
    fun startListening() {
        logger.info("Starting microphone listening")
        logger.info("Using recognizer: ${recognizer::class.simpleName}")
                recognizer.start { text -> processText(text) }
                /*soundPlayer.play("/sounds/mic_click_on.wav")*/
                isListening = true
                callbacks.onMic(true)
                startPulseEffect()
    }
    fun stopListening() {
        if (!isListening) return
        isListening = false
        callbacks.onMic(false)
        /*soundPlayer.play("/sounds/mic_click_off.wav")*/
        stopPulseEffect()
        recognizer.stop()
    }
    private fun startPulseEffect() {
        pulseTimeline = Timeline(
            KeyFrame(Duration.seconds(0.5), EventHandler {
                pulseState = !pulseState
                val color = if (pulseState) "green" else "lightgreen"
                Platform.runLater {
                    callbacks.onPulseColor(color)
                }
            })
        ).apply {
            cycleCount = Timeline.INDEFINITE
            play()
        }
    }
    private fun stopPulseEffect() {
        pulseTimeline?.stop()
        pulseTimeline = null
        Platform.runLater {
            callbacks.onPulseColor("")
        }
    }
    private fun processText(text: String?) {
        if (text.isNullOrBlank()) return
        noteEditor.append(text)
        val full = noteEditor.getText()
        callbacks.onText(full)
        logger.info("Processed text: $full")
    }
    fun triggerPulseOnce() = startPulseEffect()
    fun haltPulse() = stopPulseEffect()
    fun generatePDF(path: String) {
        noteProvider.generatePDF(path)
    }
}

