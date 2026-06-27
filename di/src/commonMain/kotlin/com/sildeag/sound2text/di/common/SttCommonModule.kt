package com.sildeag.sound2text.di.common

import com.sildeag.sound2text.core.stt.SttEngine
import org.koin.core.module.Module
import org.koin.dsl.module

val sttCommonModule: Module = module {
    single<SttEngine> { get() } // via platform module
}
