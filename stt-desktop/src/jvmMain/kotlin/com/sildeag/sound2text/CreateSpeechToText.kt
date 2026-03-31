package com.sildeag.sound2text.vosk.engine.stt

import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.pdfdesktop.SttProvider
import com.sildeag.sound2text.logging.Logger

fun createSpeechToText(settings: AppSettings, logger: Logger):
        SpeechToTextService {
    return when (settings.stt.provider) {
        SttProvider.vosk -> VoskSpeechService(
            modelPath = settings.stt.vosk?.modelPath
                ?: error("Vosk provider selected but vosk config is missing"),
                sampleRate = settings.stt.vosk?.sampleRateOverride?.toFloat()
                    ?: settings.audio.sampleRate.toFloat(),
                    logger = logger
                )
                SttProvider.whisper -> WhisperSpeechService(
            config = settings.stt.whisper
                ?: error("Whisper provider selected but whisper config is missing"),
                        audioSettings = settings.audio,
                    logger = logger
                )
            // etc.
            else -> error("Provider ${settings.stt.provider} not implemented yet")
    }
}
