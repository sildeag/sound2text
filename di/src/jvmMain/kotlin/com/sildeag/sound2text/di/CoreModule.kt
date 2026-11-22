package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.dispatchers.DefaultDispatcherProvider
import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.logging.MessageLogger
import com.sildeag.sound2text.core.pdf.discovery.SttFormManager
import org.koin.dsl.module

val coreModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
    single<Logger> { MessageLogger() }
    single {
        SttFormManager(
            scanner = get(),
            loader = get(),
            processor = get()
        )
    }
}
