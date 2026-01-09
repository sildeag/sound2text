package com.sildeag.sound2text.desktop.di


import com.sildeag.sound2text.stt.MockSpeechService
import com.sildeag.sound2text.stt.SpeechToTextService
import com.sildeag.sound2text.stt.NoOpSoundPlayer
import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.logging.ConsoleLogger
import com.sildeag.sound2text.logging.Logger
import com.sildeag.sound2text.storage.DesktopStorageService
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.SoundPlayer
import org.koin.dsl.module
fun desktopModule(settings: AppSettings) = module {
    single<Logger> { ConsoleLogger() }
    single<StorageService> { DesktopStorageService(settings) }
    single<SoundPlayer> { NoOpSoundPlayer() }
    single<SpeechToTextService> {
        // For now, always mock – real engines can come later
        MockSpeechService()
    }
}
