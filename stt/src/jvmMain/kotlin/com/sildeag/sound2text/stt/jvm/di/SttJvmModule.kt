package com.sildeag.sound2text.stt.jvm.di

import com.sildeag.sound2text.stt.SttEngine
import org.koin.dsl.module
import com.sildeag.sound2text.stt.jvm.JvmSttEngine

val SttJvmModule = module {
    single<SttEngine> { JvmSttEngine(get()) }
}
