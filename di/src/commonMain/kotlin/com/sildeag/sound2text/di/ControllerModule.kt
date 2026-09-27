package com.sildeag.sound2text.di

import com.sildeag.sound2text.uicommon.pdfwizard.controller.PdfWizardController
import com.sildeag.sound2text.appcommon.sound.Sound2TextController
import org.koin.dsl.module

val controllerModule = module {
    factory { PdfWizardController(get(), get(), get()) }
    factory { Sound2TextController(get(), get(), get()) }
}