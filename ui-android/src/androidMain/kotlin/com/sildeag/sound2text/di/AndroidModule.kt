package com.sildeag.sound2text.androidui.di

import com.sildeag.sound2text.androidui.viewmodel.AndroidSoundViewModel
import com.sildeag.sound2text.coreui.viewmodel.CoreSoundViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
/*
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
*/

package com.sildeag.sound2text.android.di
import com.sildeag.sound2text.android.audio.AndroidAudioRecorder
import com.sildeag.sound2text.android.viewmodel.AndroidSttViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
val androidModule = module {
    single { AndroidAudioRecorder() }
    viewModel {
        AndroidSttViewModel(
            audio = get(),
            stt = get(),
            pulse = get(),
            controller = get()
        )
    }
}

/*
import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.storage.AndroidStorageService
import com.sildeag.sound2text.storage.StorageService
import org.koin.dsl.module
fun androidModule(settings: AppSettings) = module {
    single { settings }
    single<Logger> {
        when (settings.services.logger) {
            "console" -> ConsoleLogger()
            "none" -> NoOpLogger()
            else -> ConsoleLogger()
        }
    }
    single<StorageService> { AndroidStorageService(get()) }
    single<SpeechToTextService> {
        when (settings.speechToText.provider) {
            "mock" -> MockSpeechService()
            else -> MockSpeechService() // until real Android STT added
        }
    }
}
*/
/*
package com.sildeag.sound2text.android.di
import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.core.SpeechToTextEngine
import com.sildeag.sound2text.android.viewmodel.AndroidSoundViewModel
import com.sildeag.sound2text.engine.android.AndroidSttEngine
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
            context = get() // Android Context from Koin
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