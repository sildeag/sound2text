package com.sildeag.sound2text.di.core.stt

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.sttandroid.AndroidSttEngine
import org.koin.core.module.Module
import org.koin.dsl.module

val sttAndroidModule: Module = module {
    single<SttEngine> { AndroidSttEngine() }
}

