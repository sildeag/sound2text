package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val platform: String = "Desktop",
    val mode: String = "DEV",
    val ui: UiSettings = UiSettings(),
    val audio: AudioSettings = AudioSettings(),
    val stt: SpeechToTextSettings = SpeechToTextSettings(),
    val services: ServiceSettings = ServiceSettings(),
    val theme: ThemeConfig,
    val logging: LoggingConfig,
) {
    companion object {
        fun default() = DefaultConfig.app
    }
}
@Serializable data class UiSettings(val type: String = "Fxml")
@Serializable
data class AudioSettings(
    val input: String = "",
    val sampleRate: Int = 16000,
    val channels: String = "Auto",
    val bitDepth: Int = 1,
    val normalize: Boolean = true,
    val downmixToMono: Boolean = true,
    val inputDevice: String = "default",
    val outputDevice: String = "default",
    val soundFiles: List<String> = emptyList()
)
@Serializable enum class AudioInputType { File, Microphone, Ffmpeg }
@Serializable enum class AudioChannels { Auto, Mono, Stereo }
@Serializable
data class SpeechToTextSettings(
    val provider: SttProvider = SttProvider.Vosk,
    val language: SttLanguage = SttLanguage.English,
    val outputFormat: SttOutputFormat = SttOutputFormat.Plain,
    val engineConfig: SttEngineConfig? = null,
    val apiKey: String = "",
    val endpoint: String = "",
    val model: String = "base"
)
@Serializable
enum class SttProvider { Vosk, Whisper, Azure, Google }
@Serializable
enum class SttOutputFormat { Plain, Json, Srt, Vtt }
@Serializable
enum class SttLanguage { English, Spanish, French,
    Russian, Arabic }
@Serializable
data class FfmpegSettings(
    val enabled: Boolean = false,
    val forceSampleRate: Boolean = true,
    val forceChannels: AudioChannels = AudioChannels.Mono,
    val decodeChunkSize: Int = 4096,
    val extraArgs: List<String> = emptyList()
)
@Serializable
data class ServiceSettings(
    val soundPlayer: String = "default",
    val logger: String = "console",
    val storage: String = "Json"
)