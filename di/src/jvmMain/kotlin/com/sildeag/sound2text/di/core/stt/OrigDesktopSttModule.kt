package com.sildeag.sound2text.di.core.stt

import com.sildeag.sound2text.core.stt.SttConfig
import org.koin.dsl.module

fun desktopSttModule(config: SttConfig): Module = module {
    single { config }
    single {
        VoskModelFactory.loadModel(get())
    }
    single<SttService> {
        VoskSttService(
            model = get(),
            config = get()
        )
    }
}