package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttEngine
import org.koin.dsl.module

actual val sttPlatformModule = module {
    single<SttEngine> { AndroidSttEngine(get(), get()) }
}
