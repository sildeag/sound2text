package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.storage.AndroidStorageService
import com.sildeag.sound2text.storage.StorageService
import org.koin.dsl.module


fun androidModule(settings: AppSettings) = module {
    single { settings }
    single<Logger> {
        when (settings.services.logger) {
            "console" -> ConsoleLogger()
            "none" -> NoOpLogger()
            else -> ConsoleLogger()
        }
    }
    single<StorageService> { AndroidStorageService(get()) }
    single<SpeechToTextService> {
        when (settings.speechToText.provider) {
            "mock" -> MockSpeechService()
            else -> MockSpeechService() // until real Android STT added
        }
    }
}