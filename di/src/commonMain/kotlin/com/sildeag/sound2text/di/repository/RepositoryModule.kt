package com.sildeag.sound2text.di.repository

import org.koin.dsl.module

val repositoryModule = module {
    single<TranscriptionRepository>
    { TranscriptionRepositoryImpl(get(), get()) }
    single<PdfRepository> { PdfRepositoryImpl(get(), get()) }
}