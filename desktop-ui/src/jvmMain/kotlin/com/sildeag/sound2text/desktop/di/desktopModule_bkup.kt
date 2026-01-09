package com.sildeag.sound2text.desktop.di
/*
import com.sildeag.sound2text.stt.*
import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.logging.ConsoleLogger
import com.sildeag.sound2text.logging.Logger
import com.sildeag.sound2text.service.note.NoteEditor
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.storage.DesktopStorageService
import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.viewmodel.CoreSoundViewModel
import com.sildeag.sound2text.ui.SoundUI
import org.koin.dsl.module
fun desktopModule(settings: AppSettings) = module {
    single<Logger> { ConsoleLogger() }
    single<StorageService> { DesktopStorageService(settings) }
    single { NoteEditor() }
    single { NoteProvider(get(), get()) }
    single<SoundPlayer> {
        when (settings.services.soundPlayer) {
            "desktop" -> DesktopSoundPlayer()
            else -> NoOpSoundPlayer()
        }
    }
    single<SpeechToTextService> {
        when (settings.services.speechToText) {
            "vosk" -> VoskSpeechService(settings.paths.voskModel,
                get())
            "whisper" ->
                WhisperSpeechService(settings.paths.whisperModel, get())
            else -> MockSpeechService()
        }
    }
    single {
        PulseLogic(
            soundPlayer = get(),
            recognizer = get(),
            noteEditor = get(),
            settings = settings,
            logger = get(),
            noteProvider = get()
        )
    }
    factory { (ui: SoundUI) ->
        CoreSoundViewModel(
            ui = ui,
            pulseLogic = get(),
            noteEditor = get(),
            noteProvider = get()
        )
    }
}
*/