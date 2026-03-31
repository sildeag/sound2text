package com.sildeag.sound2text.sttdesktop.di

import org.koin.dsl.module
import com.sildeag.sound2text.sttdesktop.SttConfig

val SttCommonModule = module {
    // Shared config factory
    factory { SttConfig(
        language = TODO(),
        modelFile = TODO()
    ) }
}