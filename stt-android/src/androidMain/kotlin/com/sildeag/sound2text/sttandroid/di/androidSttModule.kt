package com.sildeag.sound2text.androidstt.di

import com.sildeag.sound2text.android.stt.AndroidSttService
import com.sildeag.sound2text.android.stt.VoskAndroidSttEngine
import com.sildeag.sound2text.android.vosk.AndroidModelLoader
import com.sildeag.sound2text.stt.SpeechToTextService
import com.sildeag.sound2text.stt.SttEngine
import org.koin.dsl.module

val AndroidSttModule = module {
    single { AndroidModelLoader(get()) }
    single<SttEngine> {
        VoskAndroidSttEngine(
            modelLoader = get()
        )
    }
    single<SpeechToTextService> {
        AndroidSttService(
            engine = get(),
            context = get()
        )
    }
}