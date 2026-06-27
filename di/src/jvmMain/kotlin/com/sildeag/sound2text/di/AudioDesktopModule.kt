package com.sildeag.sound2text.di


import com.sildeag.sound2text.core.audio.RecordingSource
import com.sildeag.sound2text.recording.desktop.DesktopRecordingSource
import org.koin.dsl.module
val audioDesktopModule = module {
    single<RecordingSource> { DesktopRecordingSource() }
}