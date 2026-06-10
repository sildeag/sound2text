package com.sildeag.sound2text.service.logic

import com.sildeag.sound2text.audio.SoundPlayer
import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.uiandroid.config.Environment
import com.sildeag.sound2text.core.model.note.NoteEditor
import com.sildeag.sound2text.core.model.note.NoteProvider
import com.sildeag.sound2text.service.recognizer.Recognizer
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.util.Duration

class PulseLogic(
    private val soundPlayer: SoundPlayer,
    private val recognizer: Recognizer,
    private val noteEditor: NoteEditor,
    private val settings: AppSettings,
    private val logger: Logger,
    private val noteProvider: NoteProvider,
    private val onPulseUpdate: (String) -> Unit,
    private val onPulseColor: (String) -> Unit,
    private var onText: (String) -> Unit,
    private var onMic: (Boolean) -> Unit,
    private var onPulse: (String) -> Unit,
    private val environment: Environment
) {
    data class PulseConfig(
        val onText: (String) -> Unit,
        val onMic: (Boolean) -> Unit,
        val onPulse: (String) -> Unit
    )

    internal var lastConfig: PulseConfig? = null
    private var pulseTimeline: Timeline? = null
    private var pulseState = true
    private var isListening = false

    fun startRecognition() {
        recognizer.start { text ->
            processText(text)
        }
    }

    fun startListening() {
        logger.log("Using recognizer: ${recognizer::class.simpleName}")
        startRecognition()
        val greenMicEmoji = "🟢🎤"
        logger.log("Recording started $greenMicEmoji")
        isListening = true
        startPulseEffect()
    }

    fun stopListening() {
        isListening = false
        onMic(false)
        stopPulseEffect()
        recognizer.stop()
    }

    private fun startPulseEffect() {
        pulseTimeline = Timeline(
            KeyFrame(Duration.seconds(0.5), EventHandler { _ ->
                pulseState = !pulseState
                val color = if (pulseState) "green" else "lightgreen"
                Platform.runLater {
                    onPulseUpdate(color)
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
            onPulseUpdate("") // Reset style
        }
    }

    fun processText(text: String?) {
        if (!text.isNullOrBlank()) {
            noteEditor.append(text)
            val fullText = noteEditor.getText()
            onText.invoke(fullText)
            logger.log("Processed: $fullText")
        }
    }

    fun pulseConfig(
        onText: (String) -> Unit,
        onMic: (Boolean) -> Unit,
        onPulse: (String) -> Unit
    ) {
        this.onText = onText
        this.onMic = onMic
        this.onPulse = onPulse
        lastConfig = PulseConfig(onText, onMic, onPulse)
    }
}
