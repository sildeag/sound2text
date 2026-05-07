package com.sildeag.sound2text.di.core.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskModelFactory
import com.sildeag.sound2text.sttdesktop.service.vosk.VoskSttService
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidSttService
import org.koin.dsl.module
import android.content.Context
import org.koin.core.module.Module

fun desktopSttModule(config: SttConfig) = module {
    single { config }
    single { VoskModelFactory.loadModel(get()) }
    single<SttService> { VoskSttService(get(), get()) }
}
fun androidSttModule(context: Context, config: SttConfig) = module {
    single { config }
    single { VoskAndroidModelFactory.loadModel(context, get()) }
    single<SttService> { VoskAndroidSttService(get(), get()) }
}
// Android module (wired in Android app)
fun androidSttModule(context: android.content.Context): Module = module {
    single { SttConfig() }
    single {
        VoskAndroidModelFactory.loadModel(context)
    }
    single<SttService> {
        VoskAndroidSttService(
            model = get(),
            config = get()
        )
    }
}
