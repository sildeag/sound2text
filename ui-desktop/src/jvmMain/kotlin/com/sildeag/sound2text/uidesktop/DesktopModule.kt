package com.sildeag.sound2text.uidesktop

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.common.logging.ConsoleLogger
import com.sildeag.sound2text.core.logging.FileLogger
import com.sildeag.sound2text.core.common.logging.Logger
import com.sildeag.sound2text.logging.NoOpLogger
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.sttdesktop.MockSpeechService
import com.sildeag.sound2text.sttdesktop.OpenAIWhisperService
import com.sildeag.sound2text.sttdesktop.WhisperCppService
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
