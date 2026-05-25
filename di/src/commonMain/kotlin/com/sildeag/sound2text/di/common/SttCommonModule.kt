package com.sildeag.sound2text.di.common

// TODO: remove engine
import
import org.koin.core.module.Module
import org.koin.dsl.module
val sttCommonModule: Module = module {
    single<SttEngine> { get() } // via platform module
}
