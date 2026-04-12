package com.sildeag.sound2text.sttdesktop.di

import com.sildeag.sound2text.core.stt.SttConfig
import org.koin.dsl.module

val SttCommonModule = module {
// Shared config factory
    factory { SttConfig() }
}
