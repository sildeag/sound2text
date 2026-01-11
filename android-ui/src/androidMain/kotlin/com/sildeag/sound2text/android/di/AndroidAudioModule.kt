package com.sildeag.sound2text.android.di

import com.sildeag.sound2text.audio.AudioRecorder
import com.sildeag.sound2text.android.audio.AndroidAudioRecorder
import org.koin.dsl.module

val androidAudioModule = module {
    single<AudioRecorder> { AndroidAudioRecorder(context = get()) }
}
