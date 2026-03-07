package com.sildeag.sound2text.config

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

/*
import Config

class DefaultConfig(
    override val environment: Environment,
    override val settings: AppSettings,
    override val flags: FeatureFlags
) : Config

 */