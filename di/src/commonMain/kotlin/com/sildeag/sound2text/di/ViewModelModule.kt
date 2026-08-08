package com.sildeag.sound2text.di

import org.koin.dsl.module
import com.sildeag.sound2text.uicommon.viewmodel.*
val viewModelModule = module {
    factory { SttViewModel(get(), get()) }
    factory { PdfViewModel(get(), get()) }
    factory { RecordingViewModel(get(), get()) }
    factory { TranscriptListViewModel(get(), get()) }
    factory { ThemeViewModel(get()) }
    factory { NavigationViewModel(get()) }
}
