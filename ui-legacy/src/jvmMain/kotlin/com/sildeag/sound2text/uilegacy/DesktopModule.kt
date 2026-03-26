package com.sildeag.sound2text.uidesktop

import com.sildeag.sound2text.pdf.AppSettings
import com.sildeag.sound2text.logging.ConsoleLogger
import com.sildeag.sound2text.logging.FileLogger
import com.sildeag.sound2text.logging.Logger
import com.sildeag.sound2text.logging.NoOpLogger
import com.sildeag.sound2text.storage.DesktopStorageService
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.MockSpeechService
import com.sildeag.sound2text.stt.OpenAIWhisperService
import com.sildeag.sound2text.stt.SpeechToTextService
import com.sildeag.sound2text.stt.WhisperCppService
import org.koin.dsl.module
import java.io.File
fun desktopModule(settings: AppSettings) = module {
    single { settings }
    single<Logger> {
        when (settings.services.logger) {
            "console" -> ConsoleLogger()
            "file" -> FileLogger(File("sound2text.log"))
            "none" -> NoOpLogger()
            else -> ConsoleLogger()
        }
    }
    single<StorageService> { DesktopStorageService(settings) }
    single<SpeechToTextService> {
        if (settings.mode == "TEST") {
            MockSpeechService()
        } else {
            when (settings.speechToText.provider) {
                "whisper" -> WhisperCppService()
                "openai" ->
                    OpenAIWhisperService(settings.speechToText.apiKey)
                "mock" -> MockSpeechService()
                else -> MockSpeechService()
            }
        }
    }
}
