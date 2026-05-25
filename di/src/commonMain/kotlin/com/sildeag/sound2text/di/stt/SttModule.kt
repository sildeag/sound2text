package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
// TODO: remove engine
importRegistry.get

import org.koin.dsl.module
import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import org.koin.core.module.Module

fun desktopSttModule(config: SttConfig) = module {
    single { config }
    single { VoskModelFactory.loadModel(get()) }
    single<SttService> { VoskSttService(get(), get()) }
}
fun androidSttModule(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context, config: SttConfig) = module {
    single { config }
    single { VoskAndroidModelFactory.loadModel(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context, get()) }
    single<SttService> { VoskAndroidSttService(get(), get()) }
}

