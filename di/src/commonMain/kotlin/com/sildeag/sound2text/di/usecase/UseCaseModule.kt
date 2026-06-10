package com.sildeag.sound2text.di.usecase

val useCaseModule = module {
    factory { ProcessAudioUseCase(get(), get()) }
    factory { LoadPdfUseCase(get(), get()) }
}