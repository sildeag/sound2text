package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.usecase.LoadPdfUseCase
import com.sildeag.sound2text.core.usecase.ProcessAudioUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { ProcessAudioUseCase(get()) }
    single { LoadPdfUseCase(get()) }
}