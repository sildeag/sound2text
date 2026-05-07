package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.sttdesktop.DesktopSttEngine
import org.koin.core.module.Module
import org.koin.dsl.module
val sttDesktopModule: Module = module {
    single<SttEngine> { DesktopSttEngine() }
}
