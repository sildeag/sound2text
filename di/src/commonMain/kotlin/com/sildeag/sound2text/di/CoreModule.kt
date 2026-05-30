package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.stt.SpeechToTextService
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.logging.ConsoleLogger
import com.sildeag.sound2text.core.storage.StorageService
import org.koin.dsl.module

val coreModule = module {
    // Logging
    single<Logger> { ConsoleLogger() }
    // Storage (pure domain)
    single<StorageService> { // TODO: use
StorageService via DI: // TODO: use
StorageService via DI: // TODO: use
StorageService via DI: FileStorageService(get()) }
    // STT interface (implementation provided by vosk-engine)
    factory<SpeechToTextService> { error("No STT engine bound yet") }
}
