package com.sildeag.sound2text.ui.di
import com.sildeag.sound2text.stt.SttViewModel
import com.sildeag.sound2text.pdf.PdfViewModel
import com.sildeag.sound2text.debug.CapabilityDebugViewModel
import com.sildeag.sound2text.uicommon.pdf.PdfViewModel
import com.sildeag.sound2text.uicommon.viewmodel.CapabilityDebugViewModel
import org.koin.dsl.module
val uiDesktopModule = module {
    // STT screen
    single { SttViewModel(get(), get()) }
    // PDF screen
    single { PdfViewModel(get(), get()) }
    // Debug screen
    single { CapabilityDebugViewModel(get(), get()) }
}
