package com.sildeag.sound2text.uicommon.di

import org.koin.dsl.module
import com.sildeag.sound2text.uicommon.viewmodel.*
val uiCommonModule = module {
    // Navigation
    single { NavigationViewModel() }
    // Theme
    single { ThemeViewModel() }
    // Recording
    single { RecordingViewModel(get(), get()) }
    // STT
    single { SttViewModel(get(), get()) }
    // Transcripts
    single { TranscriptListViewModel(get(), get()) }
    // PDF
    single { PdfViewModel(get(), get(), get()) }
    single { PdfWizardViewModel(get()) }
}