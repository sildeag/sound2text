package com.sildeag.sound2text.desktop.di

import com.sildeag.sound2text.desktop.audio.DesktopAudioRecorder
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

val desktopModule = module {
    singleOf(::DesktopAudioRecorder)
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