package com.sildeag.sound2text.di.workflow

import com.sildeag.sound2text.core.storage.StorageService
import com.sildeag.sound2text.core.workflow.PdfWizardController
import com.sildeag.sound2text.core.storage.FileStorageService
import org.koin.dsl.module

val workflowModule = module {
    // Storage
    single<StorageService> { FileStorageService(get()) }
    // PDF wizard
    single { PdfWizardController(get(), get()) }
}
