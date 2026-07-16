package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.repository.TranscriptionRepository
import com.sildeag.sound2text.core.repository.PdfRepository
import com.sildeag.sound2text.featurerecording.data.TranscriptionRepositoryImpl
import com.sildeag.sound2text.core.pdf.data.PdfRepositoryImpl
import com.sildeag.sound2text.core.usecase.ProcessAudioUseCase
import com.sildeag.sound2text.core.usecase.LoadPdfUseCase
import org.koin.dsl.module
val repositoryModule = module {
    single<TranscriptionRepository>
    { TranscriptionRepositoryImpl(get()) }
    single<PdfRepository> { PdfRepositoryImpl(get()) }
}
val useCaseModule = module {
    single { ProcessAudioUseCase(get()) }
    single { LoadPdfUseCase(get()) }
}
