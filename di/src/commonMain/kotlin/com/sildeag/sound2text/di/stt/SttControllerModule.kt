package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttStreamingController
import org.koin.dsl.module

val controllerModule = module {
    single { SttStreamingController(get()) }
}