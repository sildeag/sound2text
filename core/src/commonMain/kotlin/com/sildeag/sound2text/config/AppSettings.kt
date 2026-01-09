package com.sildeag.sound2text.config

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val platform: String = "desktop",
    val mode: String = "DEV",
    val ui: UiSettings = UiSettings(),
    val audio: AudioSettings = AudioSettings(),
    val speechToText: SpeechToTextSettings = SpeechToTextSettings(),
    val services: ServiceSettings = ServiceSettings()
) {
    companion object {
        fun default() = AppSettings()
    }
}
@Serializable data class UiSettings(val type: String = "fxml")
@Serializable data class AudioSettings(
    val inputDevice: String = "default",
    val outputDevice: String = "default",
    val soundFiles: List<String> = emptyList()
)
@Serializable data class SpeechToTextSettings(
    val provider: String = "mock",
    val model: String = "base",
    val apiKey: String = "",
    val endpoint: String = ""
)
@Serializable data class ServiceSettings(
    val soundPlayer: String = "default",
    val logger: String = "console",
    val storage: String = "json"
)

/*
@Serializable
data class AppSettings(
    val recognizer: RecognizerSettings,
    val pulseLogic: PulseLogicParams,
    val environment: Environment,
    val uiMode: UiMode
) {
    companion object {
        fun default() = AppSettings(
            recognizer = RecognizerSettings.default(),
            pulseLogic = PulseLogicParams(threshold = 0.01,
                windowSize = 2048),
            environment = Environment.PROD,
            uiMode = UiMode.System
        )
    }
}
*/
/*
data class AppSettings(
    val soundDirectory: String,
    val stylePath: String,
    val defaultLanguage: String,
    val enableLogging: Boolean
)

 */