package com.sildeag.sound2text.core.config

class DesktopConfig : Config {
    override val environment = Environment.Development
    override val settings = AppSettings()
    override val flags = FeatureFlags()
    override val platform = Platform.Desktop
}

