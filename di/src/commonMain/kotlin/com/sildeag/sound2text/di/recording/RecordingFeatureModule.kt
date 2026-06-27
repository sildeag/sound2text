package com.sildeag.sound2text.di.recording

import com.sildeag.sound2text.featurerecording.viewmodel.SttRecordingViewModel
import org.koin.dsl.module
val recordingFeatureModule = module {
    factory { SttRecordingViewModel(get(), get()) }
}
