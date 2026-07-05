package com.sildeag.sound2text.core.config

import kotlinx.serialization.Serializable
/*
@Serializable
data class AppSettings(
    val version: Int = 1,
    val selectedEngine: String? = null,
    val selectedPdfProcessor: String? = null,
    val language: String = "en",
    val autoSaveNotes: Boolean = true,
    val enableVoiceInput: Boolean = true,
    val theme: AppTheme = AppTheme.System
)
*/

@Serializable
data class AppSettings(
    val mode: AppMode = AppMode.DEV,
    val ui: UiSettings = UiSettings(),
    val audio: AudioSettings = AudioSettings(),
    val stt: SttSettings = SttSettings(),
    val services: ServiceSettings = ServiceSettings(),
    val theme: AppTheme = AppTheme.System,
    val logging: LoggingConfig = LoggingConfig()
) {

}

@Serializable
enum class AppMode {
    DEV,
    PROD,
    TEST
}
@Serializable
data class UiSettings(
    val type: String = "Compose",
    val lastScreen: String = "Home",
    val windowWidth: Int = 1200,
    val windowHeight: Int = 800,
    val sidebarExpanded: Boolean = true
)
@Serializable
data class AudioSettings(
    val inputDevice: String = "default",
    val outputDevice: String = "default",
    val sampleRate: Int = 16_000,
    val channels: Int = 1,
    val bitDepth: Int = 16,
    val normalize: Boolean = true,
    val noiseSuppression: Boolean = true,
    val autoGain: Boolean = false,
    val bufferSize: Int = 4096,
    val soundFiles: List<String> = emptyList()
)
@Serializable
data class SpeechToTextSettings(
    val provider: SttProvider = SttProvider.Vosk,
    val language: SttLanguage = SttLanguage.English,
    val outputFormat: SttOutputFormat = SttOutputFormat.Plain,
    val model: String = "base",
    val apiKey: String = "",
    val endpoint: String = "",
    val engine: EngineConfig = EngineConfig()
)
@Serializable
enum class SttProvider {
    Vosk,
    Whisper,
    Remote
}
@Serializable
enum class SttLanguage {
    English,
    Spanish,
    French,
    German,
    Other
}
@Serializable
enum class SttOutputFormat {
    Plain,
    Json,
    Srt
}
@Serializable
data class EngineConfig(
    val beamWidth: Int = 500,
    val maxAlternatives: Int = 1,
    val enablePunctuation: Boolean = true,
    val enableDiarization: Boolean = false,
    val vadSensitivity: Float = 0.5f
)
@Serializable
data class ServiceSettings(
    val baseUrl: String = "",
    val timeoutSeconds: Int = 30,
    val retryCount: Int = 3,
    val apiVersion: String = "v1"
)
@Serializable
data class ThemeConfig(
    val mode: ThemeMode = ThemeMode.Light,
    val primary: String = "#FFFFFF",
    val accent: String = "#448AFF",
    val highContrast: Boolean = false
)
@Serializable
enum class ThemeMode {
    Light,
    Dark,
    System
}
@Serializable
data class LoggingConfig(
    val enabled: Boolean = true,
    val level: LogLevel = LogLevel.Info,
    val filePath: String = "",
    val maxFileSizeKb: Int = 1024,
    val rotate: Boolean = true
)
@Serializable
enum class LogLevel {
    Trace,
    Debug,
    Info,
    Warn,
    Error
}

@Serializable
enum class AppTheme {
    Light, Dark, System
}
