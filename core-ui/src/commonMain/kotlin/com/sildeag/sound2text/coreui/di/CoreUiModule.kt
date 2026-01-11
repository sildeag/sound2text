package com.sildeag.sound2text.coreui.di

import com.sildeag.sound2text.coreui.logic.PulseLogic
import com.sildeag.sound2text.coreui.logic.TranscriptionController
import com.sildeag.sound2text.coreui.viewmodel.CoreSoundViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val coreUiModule = module {
    singleOf(::PulseLogic)
    singleOf(::TranscriptionController)
    singleOf(::CoreSoundViewModel)
}

/*
package com.sildeag.sound2text.coreui.di

import com.sildeag.sound2text.coreui.logic.TranscriptionController
import com.sildeag.sound2text.coreui.logic.PulseLogic
import org.koin.dsl.module
val coreUiModule = module {
    // Shared UI state logic
    single { PulseLogic() }
    // Shared transcription orchestration
    single { TranscriptionController(get(), get()) }
}

 */
