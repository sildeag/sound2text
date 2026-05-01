package com.sildeag.sound2text.di.core.stt

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttService

import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidModelFactory
import com.sildeag.sound2text.sttandroid.service.vosk.VoskAndroidSttService
import org.koin.dsl.module
import android.content.Context


fun androidSttModule(context: Context, config: SttConfig) = module {
    single { config }
    single { VoskAndroidModelFactory.loadModel(context, get()) }
    single<SttService> { VoskAndroidSttService(get(), get()) }
}

/*
import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.sttandroid.AndroidSttEngine
import org.koin.core.module.Module
import org.koin.dsl.module

val sttAndroidModule: Module = module {
    single<SttEngine> { AndroidSttEngine() }
}

