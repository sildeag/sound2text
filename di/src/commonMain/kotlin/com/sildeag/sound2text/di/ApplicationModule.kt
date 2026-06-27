package com.sildeag.sound2text.di

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
