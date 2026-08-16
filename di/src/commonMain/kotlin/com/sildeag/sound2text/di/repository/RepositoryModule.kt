package com.sildeag.sound2text.di.repository

import com.sildeag.sound2text.core.pdf.resources.PdfRepositoryImpl
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.core.repository.TranscriptionRepository

import org.koin.dsl.module

val repositoryModule = module {
    single<TranscriptionRepository>
    { TranscriptionRepositoryImpl(get(), get()) }
    single<PdfRepository> { PdfRepositoryImpl(get(), get()) }
}