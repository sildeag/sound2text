package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
import com.sildeag.sound2text.core.stt.SttEngineRegistry.get

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

