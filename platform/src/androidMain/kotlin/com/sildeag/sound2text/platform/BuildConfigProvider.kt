package com.sildeag.sound2text.platform

import com.sildeag.sound2text.core.config.BuildConfig


actual object BuildConfigProvider {
    actual val buildConfig: BuildConfig =
        if (PlatformBuildConfig.DEBUG) BuildConfig.Debug else BuildConfig.Release
}

