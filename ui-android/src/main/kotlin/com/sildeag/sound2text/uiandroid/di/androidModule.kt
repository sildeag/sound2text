package com.sildeag.sound2text.androidui.di

import com.sildeag.sound2text.androidui.viewmodel.AndroidSoundViewModel
import com.sildeag.sound2text.coreui.viewmodel.CoreSoundViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AndroidModule = module {
    // Shared ViewModel
    single { CoreSoundViewModel(get(), get(), get()) }
    // Android wrapper ViewModel
    viewModel {
        AndroidSoundViewModel(
            core = get(),
            stt = get() // Provided by AndroidSttModule
        )
    }
}
/*
import com.sildeag.sound2text.android.audio.AndroidAudioRecorder
import com.sildeag.sound2text.coreui.viewmodel.CoreSoundViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val androidModule = module {
    singleOf(::AndroidAudioRecorder)
    viewModel {
        CoreSoundViewModel(
            audio = get(),
            controller = get(),
            pulse = get()
        )
    }
}
*/

/*
package com.sildeag.sound2text.android.di


import com.sildeag.sound2text.config.AppSettings
// TODO: remove engine
import
import com.sildeag.sound2text.android.viewmodel.AndroidSoundViewModel
// TODO: remove engine
import
import com.sildeag.sound2text.ui.PulseLogic
import com.sildeag.sound2text.di.SoundViewModelParams
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
val androidModule = module {
    // Settings (temporary simple version)
    single {
        AppSettings(
            modelPath = "models/vosk-model-small-en-us-0.15"
        )
    }
    // Android STT engine
    single<SpeechToTextEngine> {
        val settings = get<AppSettings>()
        AndroidSttEngine(
            modelPath = settings.modelPath,
            // TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = get() // Android // TODO: inject platform // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context from Koin
        )
    }
    // Shared UI logic
    single { PulseLogic() }
    // Android ViewModel with params
    viewModel { (params: SoundViewModelParams) ->
        AndroidSoundViewModel(
            engine = get(),
            pulse = get(),
            params = params
        )
    }
}
*/
