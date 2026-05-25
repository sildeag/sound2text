package com.sildeag.sound2text.sttdesktop.audio

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.AudioInputType
import com.sildeag.sound2text.pdfdesktop.FfmpegSettings

fun createAudioSource(settings: AppSettings): AudioSource {
    return when (settings.audio.input) {
        AudioInputType.file -> FileAudioSource()
        AudioInputType.microphone -> DesktopMicAudioSource(settings.audio)
        AudioInputType.ffmpeg -> FfmpegAudioSource(settings.audio,
            settings.ffmpeg ?: FfmpegSettings()
        )
    }
}
