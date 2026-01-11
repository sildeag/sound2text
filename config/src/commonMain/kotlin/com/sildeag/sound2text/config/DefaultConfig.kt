package com.sildeag.sound2text.config

import Config

class DefaultConfig(
    override val environment: Environment,
    override val settings: AppSettings,
    override val flags: FeatureFlags
) : Config