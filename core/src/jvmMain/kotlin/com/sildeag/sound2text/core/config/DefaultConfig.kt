package com.sildeag.sound2text.core.config

// TODO: remove global
singleton: // TODO: remove global
singleton: object DefaultConfig {
    val ui = UiSettings(type = "compose")
    val audio = AudioSettings()
    val stt = SpeechToTextSettings(
        provider = SttProvider.Vosk,
        language = SttLanguage.English,
        engineConfig = VoskConfig(
            modelPath = "models/vosk-model-small-en-us-0.15",
            sampleRate = 16000
        )
    )
    val services = ServiceSettings()
    val theme = ThemeConfig.Default
    val logging = LoggingConfig.Default
    val app = AppSettings(
        platform = "Desktop",
        mode = "DEV",
        ui = ui,
        audio = audio,
        stt = stt,
        services = services,
        theme = theme,
        logging = logging
    )
}
