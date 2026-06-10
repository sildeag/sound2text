package com.sildeag.sound2text.di.repository

val repositoryModule = module {
    single<TranscriptionRepository>
    { TranscriptionRepositoryImpl(get(), get()) }
    single<PdfRepository> { PdfRepositoryImpl(get(), get()) }
}