package com.sildeag.sound2text.uidesktop.di

import com.sildeag.sound2text.core.stt.SttEngine
import com.sildeag.sound2text.core.stt.SttService
import com.sildeag.sound2text.uidesktop.audio.DesktopAudioRecorder
import com.sildeag.sound2text.uidesktop.stt.vosk.VoskDesktopSttEngine
import com.sildeag.sound2text.featurerecording.recording.DesktopRecordingSource
import com.sildeag.sound2text.featurerecording.recording.RecordingSource

import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val desktopModule = module {
    single<RecordingSource> { DesktopRecordingSource() }
    single<SttEngine> { VoskDesktopSttEngine(get()) }
    single { SttService(get(), get()) }
}

/*
package com.sildeag.sound2text.desktop.di

import com.sildeag.sound2text.desktop.audio.DesktopAudioRecorder
import com.sildeag.sound2text.desktop.viewmodel.DesktopSttViewModel
import org.koin.dsl.module

val desktopModule = module {
    // Desktop audio capture
    single { DesktopAudioRecorder() }
    
    // Desktop ViewModel
    // Note: We use factory instead of viewModel for Desktop/Multiplatform
    factory { DesktopSttViewModel() }
}
*/
