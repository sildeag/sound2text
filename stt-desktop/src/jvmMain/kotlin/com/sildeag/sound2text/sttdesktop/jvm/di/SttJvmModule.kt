package com.sildeag.sound2text.sttdesktop.jvm.di

import com.sildeag.sound2text.sttdesktop.SttEngine
import org.koin.dsl.module
import com.sildeag.sound2text.sttdesktop.jvm.JvmSttEngine

val SttJvmModule = module {
    single<SttEngine> { JvmSttEngine(get()) }
}
