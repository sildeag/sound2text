package com.sildeag.sound2text.di.usecase

import com.sildeag.sound2text.core.usecase.LoadPdfUseCase
import com.sildeag.sound2text.core.usecase.ProcessAudioUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { ProcessAudioUseCase(get(), get()) }
    factory { LoadPdfUseCase(get(), get()) }
}