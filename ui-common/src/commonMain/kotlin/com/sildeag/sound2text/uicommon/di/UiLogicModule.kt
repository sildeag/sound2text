package com.sildeag.sound2text.uicommon.di

import com.sildeag.sound2text.uicommon.logic.TranscriptionController
import com.sildeag.sound2text.uicommon.logic.PulseLogic
import org.koin.dsl.module
val uiLogicModule = module {
    factory { TranscriptionController(get(), get()) }
    factory { PulseLogic() }
}
