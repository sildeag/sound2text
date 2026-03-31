package com.sildeag.sound2text.vosk.engine.stt

import com.sildeag.sound2text.pdfdesktop.AppSettings
import com.sildeag.sound2text.pdfdesktop.AudioInputType
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