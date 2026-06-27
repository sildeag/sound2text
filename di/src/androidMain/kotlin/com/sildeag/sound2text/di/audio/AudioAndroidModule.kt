package com.sildeag.sound2text.di.audio

import com.sildeag.sound2text.core.audio.RecordingSource

import org.koin.dsl.module
val audioAndroidModule = module {
    single<RecordingSource> { AndroidRecordingSource() }
}