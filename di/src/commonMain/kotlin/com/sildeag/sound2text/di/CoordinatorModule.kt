package com.sildeag.sound2text.di

import org.koin.dsl.module
import com.sildeag.sound2text.uicommon.logic.*
val coordinatorModule = module {
    factory { RecordingCoordinator(get(), get()) }
    factory { TranscriptionController(get(), get()) }
}
