package com.sildeag.sound2text.stt.di

import org.koin.dsl.module
import com.sildeag.sound2text.stt.SttConfig

val SttCommonModule = module {
    // Shared config factory
    factory { SttConfig(
        language = TODO(),
        modelFile = TODO()
    ) }
}