package com.sildeag.sound2text.platform

import com.sildeag.sound2text.core.config.BuildConfig
import java.lang.management.ManagementFactory

actual object BuildConfigProvider {
    actual val buildConfig: BuildConfig =
        if (ManagementFactory.getRuntimeMXBean()
                .inputArguments.any { it.contains("jdwp") }
        ) BuildConfig.Debug else BuildConfig.Release
}
