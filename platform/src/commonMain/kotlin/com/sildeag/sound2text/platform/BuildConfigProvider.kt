package com.sildeag.sound2text.platform

import com.sildeag.sound2text.core.config.BuildConfig

expect object BuildConfigProvider {
    val buildConfig: BuildConfig
}
