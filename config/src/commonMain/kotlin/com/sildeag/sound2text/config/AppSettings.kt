package com.sildeag.sound2text.config

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val platform: String = "Desktop",
    val mode: String = "DEV",
    val ui: UiSettings = UiSettings(),
    val audio: AudioSettings = AudioSettings(),
    val stt: SpeechToTextSettings = SpeechToTextSettings(),
    val services: ServiceSettings = ServiceSettings()
) {
    companion object {
        fun default() = AppSettings()
    }
}
@Serializable data class UiSettings(val type: String = "Fxml")
@Serializable data class AudioSettings(
    val input: String = "", // "file" | "microphone" | "ffmpeg"
    val sampleRate: Int = 16000,
    val channels: String= "Auto", // "auto" | "mono" | "stereo"
    val bitDepth: Int=1,
    val normalize: Boolean=true,
    val downmixToMono: Boolean=true, // if stereo, mix to mono before
    val inputDevice: String = "default",
    val outputDevice: String = "default",
    val soundFiles: List<String> = emptyList()
)

@Serializable
enum class AudioInputType { File, Microphone, Ffmpeg }
@Serializable
enum class AudioChannels { Auto, Mono, Stereo }

@Serializable data class SpeechToTextSettings(
    val provider: SttProvider = SttProvider.Vosk,
    val modelPath: String="models/vosk-model-small-en-us-0.15",
    val language: SttLanguage = SttLanguage.English,
    val outputFormat: SttOutputFormat = SttOutputFormat.Plain,
    val vosk: VoskConfig? = null,
    val whisper: WhisperConfig? = null,
    val azure: AzureConfig? = null,
    val google: GoogleConfig? = null,
    val model: String = "base",
    val apiKey: String = "",
    val endpoint: String = ""
)

@Serializable
enum class SttProvider { Vosk, Whisper, Azure, Google }
@Serializable
enum class SttOutputFormat { Plain, Json, Srt, Vtt }
// Language
@Serializable
enum class SttLanguage { English, Spanish, French, Russian, Arabic}
// Vosk
@Serializable
/*
data class VoskConfig(
    val modelPath: String,
    val sampleRateOverride: Int? = null,
    val useGrammar: Boolean = false,
    val grammar: List<String> = emptyList()
)

 */
data class VoskConfig(
    val modelPath: String,
    val sampleRate: Int = 16000,
    val maxAlternatives: Int = 0,
    val enableWords: Boolean = true,
    val enablePartialResults: Boolean = true,
    val logLevel: Int = 0
)

// Whisper
@Serializable
data class WhisperConfig(
    val modelPath: String,
    val task: WhisperTask = WhisperTask.Transcribe,
    val language: String? = null,
    val temperature: Double = 0.0,
    val beamSize: Int = 5,
    val bestOf: Int = 3
)
@Serializable
enum class WhisperTask { Transcribe, Translate }
// Azure
@Serializable
data class AzureConfig(
    val key: String,
    val region: String,
    val endpoint: String? = null,
    val language: String
)
// Google
@Serializable
data class GoogleConfig(
    val credentialsPath: String,
    val language: String,
    val useEnhancedModel: Boolean = false
)

@Serializable
data class FfmpegSettings(
    val enabled: Boolean = false,
    val forceSampleRate: Boolean = true,
    val forceChannels: AudioChannels = AudioChannels.Mono,
    val decodeChunkSize: Int = 4096,
    val extraArgs: List<String> = emptyList()
)
@Serializable data class ServiceSettings(
    val soundPlayer: String = "default",
    val logger: String = "console",
    val storage: String = "Json"
)
