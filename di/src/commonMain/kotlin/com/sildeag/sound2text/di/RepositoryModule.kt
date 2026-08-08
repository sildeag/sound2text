package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.repository.TranscriptionRepository
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.featurerecording.data.TranscriptionRepositoryImpl
import com.sildeag.sound2text.core.pdf.data.PdfRepositoryImpl

import org.koin.dsl.module
/*
val repositoryModule = module {
    single<TranscriptionRepository>
    { TranscriptionRepositoryImpl(get()) }
    single<PdfRepository> { PdfRepositoryImpl(get()) }
}
*/

import org.koin.dsl.module
import com.sildeag.sound2text.core.transcript.*
import com.sildeag.sound2text.core.settings.*
val repositoryModule = module {
    single<TranscriptRepository> { TranscriptRepositoryImpl() }
    single<SettingsRepository> { SettingsRepositoryImpl() }
}
