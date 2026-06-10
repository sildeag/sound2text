package com.sildeag.sound2text

import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.logging.ConsoleLogger
import com.sildeag.sound2text.logging.NoOpLogger
import com.sildeag.sound2text.storage.AndroidStorageService
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.MockSpeechService
import com.sildeag.sound2text.stt.SpeechToTextService
import org.koin.android.ext.koin.android// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
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
    single<StorageService>
    { AndroidStorageService(android// TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context()) }
    single<SpeechToTextService> {
        // For now, use mock for Android until a real engine is wired
        MockSpeechService()
    }
}
