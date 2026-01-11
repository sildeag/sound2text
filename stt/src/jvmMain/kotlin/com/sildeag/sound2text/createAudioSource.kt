package com.sildeag.sound2text.vosk.engine.stt

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.config.AudioInputType
import com.sildeag.sound2text.config.FfmpegSettings

fun createAudioSource(settings: AppSettings): AudioSource {
    return when (settings.audio.input) {
        AudioInputType.file -> FileAudioSource()
        AudioInputType.microphone -> DesktopMicAudioSource(settings.audio)
        AudioInputType.ffmpeg -> FfmpegAudioSource(settings.audio,
            settings.ffmpeg ?: FfmpegSettings()
        )
    }
}