package com.sildeag.sound2text.core.config

object DefaultConfig {
    val app = AppSettings(
        platform = "Desktop",
        mode = "DEV",
        ui = UiSettings(type = "compose"),
        audio = AudioSettings(),
        stt = SpeechToTextSettings(),
        services = ServiceSettings(),
        theme = ThemeConfig.Default,
        logging = LoggingConfig.Default
    )
}