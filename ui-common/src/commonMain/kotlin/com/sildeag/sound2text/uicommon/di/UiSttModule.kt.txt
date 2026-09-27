package com.sildeag.sound2text.uicommon.di

import com.sildeag.sound2text.uicommon.state.SttState
import com.sildeag.sound2text.uicommon.state.SttEvent
import com.sildeag.sound2text.uicommon.state.SttAction
import com.sildeag.sound2text.uicommon.stt.SttUiState
import com.sildeag.sound2text.uicommon.stt.SttUiLifecycle
import com.sildeag.sound2text.uicommon.stt.vosk.VoskEnginePlugin
import com.sildeag.sound2text.uicommon.sound.SoundEngine
import com.sildeag.sound2text.uicommon.sound.SoundState
import org.koin.dsl.module
val uiSttModule = module {
    factory { SttState() }
    factory { SttEvent() }
    factory { SttAction() }
    factory { SttUiState() }
    factory { SttUiLifecycle(get(), get()) }
    factory { SoundState() }
    factory { SoundEngine(get()) }
    factory { VoskEnginePlugin(get()) }
}
