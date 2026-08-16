package com.sildeag.sound2text.di

import org.koin.dsl.module
/*
val repositoryModule = module {
    single<TranscriptionRepository>
    { TranscriptionRepositoryImpl(get()) }
    single<PdfRepository> { PdfRepositoryImpl(get()) }
}
*/

import com.sildeag.sound2text.core.transcript.*
import com.sildeag.sound2text.core.settings.*
val repositoryModule = module {
    single<TranscriptRepository> { TranscriptRepositoryImpl() }
    single<SettingsRepository> { SettingsRepositoryImpl() }
}
