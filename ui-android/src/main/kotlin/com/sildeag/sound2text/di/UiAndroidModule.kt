package com.sildeag.sound2text.di

import com.sildeag.sound2text.uicommon.pdf.PdfViewModel
import org.koin.dsl.module

val uiAndroidModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { PdfViewModel(get(), get()) }
}