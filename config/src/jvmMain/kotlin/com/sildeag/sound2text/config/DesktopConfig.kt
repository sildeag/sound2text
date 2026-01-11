package com.sildeag.sound2text.config

import Config

class DesktopConfig : Config {
    override val environment = Environment.Development
    override val settings = AppSettings()
    override val flags = FeatureFlags()
}