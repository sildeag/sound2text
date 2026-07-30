package com.sildeag.sound2text.di

import org.koin.dsl.module

startKoin {
    modules(
        coreModule,
        audioModule,
        sttModule,
        notesModule,
        workflowModule,
        appModule,
        platformModule // android or jvm
    )
}
